package org.man.process.service.delegate;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.man.process.model.request.StudentInfoRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


import static org.man.process.constant.CommonConstants.*;


@Component
@Slf4j
public class SubmissionFileToThirdParty implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders httpHeaders = (HttpHeaders) delegateExecution.getVariable(TASK_VARIABLE_HTTP_HEADER);
        StudentInfoRequest studentInfoRequest = OBJECT_MAPPER.readValue(delegateExecution.getVariable(TASK_VARIABLE_CAMUNDA_FILE_SUBMISSION_REQUEST).toString(), StudentInfoRequest.class);
        log.info("Start task:{}, header:{}, request:{} ", TASK_VARIABLE_CAMUNDA_FILE_SUBMISSION_REQUEST, httpHeaders, studentInfoRequest);

        log.info("Complete task:{}, header:{}, request:{} ", TASK_VARIABLE_CAMUNDA_FILE_SUBMISSION_REQUEST, httpHeaders, studentInfoRequest);
    }
}
