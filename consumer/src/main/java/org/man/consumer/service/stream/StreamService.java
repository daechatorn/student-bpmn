package org.man.consumer.service.stream;

import lombok.extern.slf4j.Slf4j;
import org.man.consumer.model.request.StudentInfoRequest;
import org.man.consumer.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


import static org.man.consumer.service.stream.StreamChannel.REGISTER_INPUT;

@Slf4j
@Service
@EnableBinding(StreamChannel.class)
public class StreamService {
    @Autowired
    StreamChannel streamChannel;

    @Autowired
    RegisterService registerService;

    private static final String LOG_PREPEND_MSG_INCOMING = "Message incoming to {}: header: {}, request: {}";

    @StreamListener(REGISTER_INPUT)
    public void registerInputListener(@Payload StudentInfoRequest request, @Header(name = "httpHeaders") HttpHeaders httpHeaders) {
        try {
            log.info(LOG_PREPEND_MSG_INCOMING, REGISTER_INPUT, httpHeaders, request);
            registerService.registerStudent(request, httpHeaders);
        }catch (Exception ex){
            log.error("Found exception");
        }
    }

}
