package com.epam.tests.api.swagger.conditions;

public class PostQueryConditions extends CommonConditions {
    protected static final String QUERY_END_POINT = "/user/createWithList";
    protected static final String HEADER_PARAM_NAME = "Content-Type";
    protected static final String HEADER_PARAM_VALUE = "application/json";
    protected static final String ERROR_RESPONSE_MESSAGE = "something bad happened";
    protected static final String SUCCESS_RESPONSE_MESSAGE = "ok";
}
