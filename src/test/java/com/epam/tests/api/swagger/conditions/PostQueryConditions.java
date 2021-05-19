package com.epam.tests.api.swagger.conditions;

public class PostQueryConditions extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/createWithList";
    private static final String HEADER_PARAM_NAME = "Content-Type";
    private static final String HEADER_PARAM_VALUE = "application/json";
    private static final String ERROR_RESPONSE_MESSAGE = "something bad happened";
    private static final String SUCCESS_RESPONSE_MESSAGE = "ok";

    public static String getSuccessResponseMessage() {
        return SUCCESS_RESPONSE_MESSAGE;
    }

    public static String getErrorResponseMessage() {
        return ERROR_RESPONSE_MESSAGE;
    }

    public static String getQueryEndPoint() {
        return QUERY_END_POINT;
    }

    public static String getHeaderParamName() {
        return HEADER_PARAM_NAME;
    }

    public static String getHeaderParamValue() {
        return HEADER_PARAM_VALUE;
    }
}
