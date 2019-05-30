package org.man.consumer.model.rule.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class StudentGradeRuleRequest implements Serializable {
    private int score;
    private boolean specialActivity;
    private String goodStudent;
}
