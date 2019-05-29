package org.man.consumer.config.header;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Configuration
public class HttpHeaderConfig {
    private static final Logger log = LoggerFactory.getLogger(HttpHeaderConfig.class);
    private static final List<String> ALLOW_HEADER_LIST = Collections.unmodifiableList(Arrays.asList("correlationid", "userid", "accept-language"));

    public HttpHeaderConfig() {
    }

    @Bean
    @RequestScope
    public HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Enumeration<String> headerNames = curRequest.getHeaderNames();
        if (headerNames != null) {
            while(headerNames.hasMoreElements()) {
                String header = (String)headerNames.nextElement();
                String value = curRequest.getHeader(header);
                if (ALLOW_HEADER_LIST.contains(header.toLowerCase())) {
                    log.info("Adding header {} with value {}", header, value);
                    httpHeaders.add(header, value);
                } else {
                    log.debug("Header {} with value {} is not required to be copied", header, value);
                }
            }
        }

        if (!httpHeaders.containsKey("accept-language") || StringUtils.isEmpty(httpHeaders.getFirst("accept-language"))) {
            httpHeaders.set("accept-language", "th");
        }

        return httpHeaders;
    }

}
