package org.man.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.man.consumer.model.Student;
import org.man.consumer.model.request.StudentInfoRequest;
import org.man.consumer.model.rule.request.StudentGradeRuleRequest;
import org.man.consumer.model.rule.response.StudentGradeRuleResponse;
import org.man.consumer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private ProcessService processService;
    @Autowired
    private DmnService dmnService;
    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(@Payload StudentInfoRequest request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        try {

            StudentGradeRuleRequest studentGradeRuleRequest = new StudentGradeRuleRequest()
                    .setScore(request.getScore())
                    .setSpecialActivity("man".equalsIgnoreCase(request.getName()))
                    .setGoodStudent("man".equalsIgnoreCase(request.getName())? "Y" : "N");

            Optional<StudentGradeRuleResponse> optionalStudentGradeRuleResponse = dmnService.checkStudentGradeRulename(studentGradeRuleRequest);
            if(!optionalStudentGradeRuleResponse.isPresent()){
                log.error("Cannot calculate student grade");
            }
            log.info("Grade: :{}", optionalStudentGradeRuleResponse.get());

            if(studentRepository.insert(request) != 1){
                log.error("Cannot insert student into database");
                throw new BusinessException(1899);
            }

            ResponseModel responseModel = processService.postProcess(request, httpHeaders);
            if(responseModel.getCode() != 1000){
                log.error("Fail on post process");
                throw new BusinessException(1899);
            }

            Optional<List<Student>> optionalCustomerList = studentRepository.getAll();
            log.info("Process done, All Student: {}", !optionalCustomerList.isPresent() ? "None student" : optionalCustomerList.get());
        }
        catch (BusinessException bx){
            log.error("Business exception: {}", bx.getCode());
        } catch (Exception ex){
            log.error("Found exception");
        }
    }

}
