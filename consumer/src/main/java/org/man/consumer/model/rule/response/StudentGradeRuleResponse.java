package org.man.consumer.model.rule.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentGradeRuleResponse implements Serializable {
    private String gradeEn;
    private int gradeTh;
}
