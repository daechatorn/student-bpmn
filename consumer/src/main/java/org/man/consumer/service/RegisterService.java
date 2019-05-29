package org.man.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.man.consumer.model.Student;
import org.man.consumer.model.request.StudentInfoRequest;
import org.man.consumer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private ProcessService processService;
    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(@Payload StudentInfoRequest request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        try {

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
