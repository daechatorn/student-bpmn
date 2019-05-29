package org.man.process.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.man.process.model.request.StudentInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.man.process.constant.CommonConstants.*;


@RestController
@Slf4j
public class ProcessController {

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/v1/process")
    public ResponseEntity<ResponseModel> process(@RequestBody StudentInfoRequest request) {
        log.info("Request incoming header:{}, body: {}", httpHeaders, request);

        HttpHeaders headers = new HttpHeaders();
        headers.putAll(httpHeaders);

        try {
            runtimeService.startProcessInstanceByKey(
                    PROCESS_KEY_STUDENT_SUBMISSION_FILE,
                    Variables.putValue(TASK_VARIABLE_HTTP_HEADER, headers).
                            putValue(TASK_VARIABLE_CAMUNDA_FILE_SUBMISSION_REQUEST, OBJECT_MAPPER.writeValueAsString(request))

            );
            return new ResponseEntity<>(new ResponseModel(1000), httpHeaders, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Fail to start process");
            throw new BusinessException(1899);
        }
    }

}
