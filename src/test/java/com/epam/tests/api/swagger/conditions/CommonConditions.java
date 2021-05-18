package com.epam.tests.api.swagger.conditions;

public abstract class CommonConditions {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String RESPONSE_BODY_PATH = "src/test/resources/response-body";
    private static final String INVALID_STATUS_CODE_MESSAGE = "Invalid status code!";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getResponseBodyPath() {
        return RESPONSE_BODY_PATH;
    }

    public static String getInvalidStatusCodeMessage() {
        return INVALID_STATUS_CODE_MESSAGE;
    }
}
