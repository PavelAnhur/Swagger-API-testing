package com.epam.tests.api.swagger.conditions;

public abstract class CommonConditions {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String INVALID_STATUS_CODE_MESSAGE = "Invalid status code!";
    private static final String INVALID_RESPONSE_BODY_MESSAGE = "Invalid response body!";
    private static final String QUERY_STATUS = "Query not correct!";

    public static String getQueryStatus() {
        return QUERY_STATUS;
    }

    public static String getInvalidResponseBodyMessage() {
        return INVALID_RESPONSE_BODY_MESSAGE;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getInvalidStatusCodeMessage() {
        return INVALID_STATUS_CODE_MESSAGE;
    }
}
