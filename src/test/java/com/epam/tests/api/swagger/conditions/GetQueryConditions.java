package com.epam.tests.api.swagger.conditions;

public class GetQueryConditions extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/";
    private static final int CODE_FROM_RESPONSE_BODY = 1;
    private static final String MESSAGE_FROM_RESPONSE_BODY = "User not found";

    public static int getCodeFromResponseBody() {
        return CODE_FROM_RESPONSE_BODY;
    }

    public static String getMessageFromResponseBody() {
        return MESSAGE_FROM_RESPONSE_BODY;
    }

    public static String getQueryEndPoint() {
        return QUERY_END_POINT;
    }
}
