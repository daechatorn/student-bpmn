package org.man.sender.service.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("squid:S1214")
public interface StreamChannel {
    String REGISTER_OUTPUT = "RegisterOutput";

    @Output(StreamChannel.REGISTER_OUTPUT)
    SubscribableChannel registerOutput();
}
