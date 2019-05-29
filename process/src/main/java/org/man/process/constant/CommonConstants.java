package org.man.process.constant;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonConstants {

    private CommonConstants() {}

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //BPMN
    //PROCESS
    public static final String PROCESS_KEY_STUDENT_SUBMISSION_FILE = "STUDENT_SUBMISSION_FILE";

    //TASK
    public static final String TASK_KEY_SUBMIT_FILE_TO_THIRD_PARTY= "SUBMIT_FILE_TO_THIRD_PARTY";

    //Variable
    public static final String TASK_VARIABLE_HTTP_HEADER = "varHttpHeader";
    public static final String TASK_VARIABLE_CAMUNDA_FILE_SUBMISSION_REQUEST = "varCamundaFileSubmissionRequest";

}
