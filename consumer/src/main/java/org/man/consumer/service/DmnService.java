package org.man.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.man.consumer.model.rule.request.StudentGradeRuleRequest;
import org.man.consumer.model.rule.response.StudentGradeRuleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.man.consumer.config.dmn.DmnConstant.*;


@Service
@Slf4j
public class DmnService {

    @Autowired
    @Qualifier("startDmnEngine")
    private DmnEngine dmnEngineBean;

    @Autowired
    @Qualifier("studentGradeDmnEngine")
    private DmnDecision studentGradeDmnDecision;

    private static final String LOG_PREPEND_CHECK_RULENAME_REQUEST = "CheckRulename Request : {}";

    public Optional<StudentGradeRuleResponse> checkStudentGradeRulename(StudentGradeRuleRequest request) {
        log.debug(LOG_PREPEND_CHECK_RULENAME_REQUEST, request);

        StudentGradeRuleResponse dmnResponse = new StudentGradeRuleResponse();
        // prepare variables for decision evaluation
        VariableMap variables = Variables
                .putValue(STUDENT_GRADE_SCORE_INPUT, request.getScore())
                .putValue(STUDENT_GRADE_SPECIAL_ACTIVITY_INPUT, request.isSpecialActivity())
                .putValue(STUDENT_GRADE_GOOD_STUDENT_INPUT, request.getGoodStudent());

        // evaluate decision
        DmnDecisionTableResult result = dmnEngineBean.evaluateDecisionTable(studentGradeDmnDecision, variables);
        // print result
        if (!result.isEmpty()) {
            dmnResponse.setGradeEn(result.getSingleResult().getEntry(STUDENT_GRADE_GRAGE_EN_OUTPUT).toString());
            dmnResponse.setGradeTh(result.getSingleResult().getEntry(STUDENT_GRADE_GRAGE_TH_OUTPUT));
            return Optional.of(dmnResponse);
        }
        log.info("CheckRulename Response : {}", dmnResponse);

        return Optional.empty();
    }

}
