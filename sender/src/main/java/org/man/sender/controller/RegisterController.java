package org.man.sender.controller;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.response.ResponseModel;
import org.man.sender.model.request.StudentInfoRequest;
import org.man.sender.model.response.StudentInfoResponse;
import org.man.sender.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    HttpHeaders httpHeaders;

    @PostMapping("/v1/register")
    public ResponseEntity<ResponseModel<StudentInfoResponse>> register(@RequestBody StudentInfoRequest request) {
        ResponseModel<StudentInfoResponse> response = registerService.regis(request);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

    }
}
