package org.man.consumer.config.dmn;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.man.consumer.service.DmnService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.InputStream;

import static org.man.consumer.config.dmn.DmnConstant.*;


@ControllerAdvice
@Slf4j
public class DmnConfig {

    @Value("${student.grade.dmn.file.path}")
    private String studentGradeDmnFilePath;

    @Bean
    public DmnEngine startDmnEngine(){
        return DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
    }

    @Bean(name = "studentGradeDmnEngine")
    public DmnDecision studentGradeDmnDecision(){
        InputStream inputStream = DmnService.class.getResourceAsStream(studentGradeDmnFilePath);
        return startDmnEngine().parseDecision(STUDENT_GRADE_RULES_TABLE_ID, inputStream);
    }

}
