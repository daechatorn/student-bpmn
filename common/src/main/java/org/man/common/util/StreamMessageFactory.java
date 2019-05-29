package org.man.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.Map;
import java.util.stream.Collectors;

public final class StreamMessageFactory {

    public StreamMessageFactory() {
    }

    public static <T> Message<T> getMessage(T payload, HttpHeaders httpHeaders) {
        MessageHeaderAccessor accessor = new MessageHeaderAccessor();
        accessor.copyHeaders((Map)httpHeaders.entrySet().stream().filter((x) -> {
            return !((String)x.getKey()).equalsIgnoreCase("correlationid");
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        HttpHeaders temp = new HttpHeaders();
        temp.putAll(httpHeaders);
        accessor.setHeader("httpHeaders", temp);
        return MessageBuilder.withPayload(payload).setHeaders(accessor).build();
    }

}
