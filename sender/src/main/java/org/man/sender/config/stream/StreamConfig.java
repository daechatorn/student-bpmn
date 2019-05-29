package org.man.sender.config.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaderMapper;

@Configuration
@EnableKafka
public class StreamConfig {
    private static final Logger log = LoggerFactory.getLogger(StreamConfig.class);

    public StreamConfig() {
    }

    @Bean(name = "headerMapper")
    public KafkaHeaderMapper headerMapper(){
        DefaultKafkaHeaderMapper defaultKafkaHeaderMapper = new DefaultKafkaHeaderMapper();
        defaultKafkaHeaderMapper.addTrustedPackages(
                "org.springframework.http",
                "java.util",
                "java.lang",
                "org.springframework.util"
        );
        log.info("**** Added trusted packages into kafka **** ");
        return defaultKafkaHeaderMapper;
    }

}
