package org.man.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.man.consumer.model.request.StudentInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ProcessService {

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestTemplate loadBalancedRestTemplate;

    @Value("${v1.process.url}")
    private String processUrlV1;

    public ResponseModel postProcess(StudentInfoRequest request, HttpHeaders httpHeaders) {
        try {
            ParameterizedTypeReference<ResponseModel> parameterizedTypeReference = new ParameterizedTypeReference<ResponseModel>() {};

            ResponseEntity<ResponseModel> responseEntity = loadBalancedRestTemplate.exchange(
                    processUrlV1,
                    HttpMethod.POST,
                    new HttpEntity<>(request, httpHeaders),
                    parameterizedTypeReference);

            return responseEntity.getBody();

        } catch (Exception ex){
            throw new BusinessException(1899);
        }
    }

}
