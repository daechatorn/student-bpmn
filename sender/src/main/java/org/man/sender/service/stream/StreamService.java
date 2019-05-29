package org.man.sender.service.stream;

import lombok.extern.slf4j.Slf4j;
import org.man.common.util.StreamMessageFactory;
import org.man.sender.model.request.StudentInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableBinding(StreamChannel.class)
public class StreamService {
    @Autowired
    StreamChannel streamChannel;

    private static final String LOG_PREPEND_MSG_SENT_TO = "Message sent to {}: {}";

    public boolean postRegisterStream(StudentInfoRequest studentInfoRequest, HttpHeaders httpHeaders){
        try {
            boolean results = streamChannel.registerOutput().send(StreamMessageFactory.getMessage(studentInfoRequest, httpHeaders));
            log.info("Message sent to {}: {} | Value: {}", StreamChannel.REGISTER_OUTPUT, results, studentInfoRequest);
            return true;
        } catch (Exception ex) {
            log.error("Unable to send message to Post Register Stream.", ex);
            return false;
        }
    }

}
