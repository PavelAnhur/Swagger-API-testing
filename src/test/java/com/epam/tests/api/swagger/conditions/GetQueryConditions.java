package com.epam.tests.api.swagger.conditions;

public class GetQueryConditions extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/";
    private static final String RESPONSE_BODY_FILE_VALID = "get-query-valid.json";
    private static final String RESPONSE_BODY_FILE_INVALID = "get-query-invalid.json";

    public static String getQueryEndPoint() {
        return QUERY_END_POINT;
    }

    public static String getResponseBodyFileValid() {
        return RESPONSE_BODY_FILE_VALID;
    }

    public static String getResponseBodyFileInvalid() {
        return RESPONSE_BODY_FILE_INVALID;
    }
}
