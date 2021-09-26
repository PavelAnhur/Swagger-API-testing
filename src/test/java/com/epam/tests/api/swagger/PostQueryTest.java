package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.model.request.Users;
import com.epam.model.response.ResponseBody;
import com.epam.enums.StatusCode;
import com.epam.tests.api.swagger.conditions.PostQueryConditions;
import com.epam.utils.JsonUtils;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;

@Slf4j
public class PostQueryTest extends PostQueryConditions {
    private final Users users;
    private final int statusCode;
    private final SoftAssert softAssert;

    @Factory(dataProvider = "dataForPostTest", dataProviderClass = DataProviderForTests.class)
    public PostQueryTest(Users users, int statusCode) {
        this.users = users;
        this.statusCode = statusCode;
        this.softAssert = new SoftAssert();
    }

    @Test
    public void swaggerPostQueryTest() {
        String requestBody;
        if (null == users) {
            requestBody = "{}";
        } else {
            String usersAsJson = JsonUtils.toJson(users);
            requestBody = JsonPath.parse(usersAsJson).read("$.users").toString();
            log.info("Request body: " + requestBody);
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                          .uri(URI.create(format("%s%s", BASE_URL, QUERY_END_POINT)))
                                          .header(HEADER_PARAM_NAME, HEADER_PARAM_VALUE)
                                          .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                                          .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
            int responseBodyCode = responseBody.getCode();
            String responseBodyMessage = responseBody.getMessage();
            if (response.statusCode() == StatusCode.OK_200.getValue()) {
                softAssert.assertEquals(responseBodyMessage, SUCCESS_RESPONSE_MESSAGE);
                softAssert.assertEquals(responseBodyCode, StatusCode.OK_200.getValue());
            } else if (response.statusCode() == StatusCode.SERVER_ERROR_500.getValue()) {
                softAssert.assertEquals(responseBodyMessage, ERROR_RESPONSE_MESSAGE);
                softAssert.assertEquals(responseBodyCode, StatusCode.SERVER_ERROR_500.getValue());
            } else {
                log.warn(QUERY_STATUS);
            }
            softAssert.assertEquals(response.statusCode(), statusCode, INVALID_STATUS_CODE_MESSAGE);
            softAssert.assertAll(INVALID_RESPONSE_BODY_MESSAGE);
        } catch (InterruptedException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
