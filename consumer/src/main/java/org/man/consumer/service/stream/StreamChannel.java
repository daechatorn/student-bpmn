package org.man.consumer.service.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("squid:S1214")
public interface StreamChannel {
    String REGISTER_INPUT = "RegisterInput";

    @Input(StreamChannel.REGISTER_INPUT)
    SubscribableChannel registerInput();
}
