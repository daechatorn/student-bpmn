package org.man.sender.service;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.man.sender.model.request.StudentInfoRequest;
import org.man.sender.model.response.StudentInfoResponse;
import org.man.sender.service.stream.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static org.man.common.util.CommonUtil.generateUUID;


@Service
@Slf4j
public class RegisterService {

    @Autowired
    HttpHeaders httpHeaders;

    @Autowired
    StreamService streamService;

    public ResponseModel<StudentInfoResponse> regis(StudentInfoRequest request){

        if(!request.getCid().matches("[0-9]+")) {
            log.error("The cid is contain text");
            throw new BusinessException(1899);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.putAll(httpHeaders);

        if(!streamService.postRegisterStream(request, headers)){
            throw new BusinessException(1899);
        }

        return new ResponseModel<StudentInfoResponse>(1000).setDataObj(new StudentInfoResponse(generateUUID()));
    }

}
