package org.man.consumer.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.man.common.model.exception.BusinessException;
import org.man.common.model.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseModel> handleGenericException(Exception ex) {

        log.error(ex.getMessage(), ex);

        // Return 9000 when the exception is caused by Read Timeout or HTTP 504
        if (ex instanceof ResourceAccessException
                || (ex instanceof HttpServerErrorException && HttpStatus.GATEWAY_TIMEOUT.equals(((HttpServerErrorException) ex).getStatusCode()))) {
            return new ResponseEntity<>(this.generateResponseModel(9000), HttpStatus.OK);
        }

        return new ResponseEntity<>(this.generateResponseModel(1999), HttpStatus.OK);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ResponseModel> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(this.generateResponseModel(Integer.parseInt(ex.getCode())), HttpStatus.OK);
    }

    private ResponseModel generateResponseModel(int responseCode){
        return new ResponseModel(responseCode);
    }
}
