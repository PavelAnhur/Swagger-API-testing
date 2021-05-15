package com.epam.tests.api.swagger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CommonConditions {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String REQUEST_BODY_PATH = "src/test/resources/request-body/%s";
    private static final String RESPONSE_BODY_PATH = "src/test/resources/response-body";
    private static final String INVALID_STATUS_CODE_MESSAGE = "Invalid status code!";
    private static Logger LOGGER;

    public CommonConditions() {
        LOGGER = LogManager.getRootLogger();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getRequestBodyPath() {
        return REQUEST_BODY_PATH;
    }

    public static String getResponseBodyPath() {
        return RESPONSE_BODY_PATH;
    }

    public static String getInvalidStatusCodeMessage() {
        return INVALID_STATUS_CODE_MESSAGE;
    }
}
