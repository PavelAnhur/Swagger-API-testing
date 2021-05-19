package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.data.request.Users;
import com.epam.data.response.ResponseBody;
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

    @Factory(dataProvider = "dataForPostTest", dataProviderClass = DataProviderForTests.class)
    public PostQueryTest(Users users, int statusCode) {
        this.users = users;
        this.statusCode = statusCode;
    }

    @Test
    public void swaggerPostQueryTest() {
        String requestBody;
        if (users == null) {
            requestBody = "{}";
        } else {
            String usersAsJson = JsonUtils.toJson(users);
            requestBody = JsonPath.parse(usersAsJson).read("$.users").toString();
            log.info("Request body: " + requestBody);
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                          .uri(URI.create(format("%s%s", getBaseUrl(), getQueryEndPoint())))
                                          .header(getHeaderParamName(), getHeaderParamValue())
                                          .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                                          .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            SoftAssert softAssert = new SoftAssert();
            ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
            int responseBodyCode = responseBody.getCode();
            String responseBodyMessage = responseBody.getMessage();
            if (response.statusCode() == StatusCode.OK_200.getValue()) {
                softAssert.assertEquals(responseBodyMessage, getSuccessResponseMessage());
                softAssert.assertEquals(responseBodyCode, StatusCode.OK_200.getValue());
            } else if (response.statusCode() == StatusCode.SERVER_ERROR_500.getValue()) {
                softAssert.assertEquals(responseBodyMessage, getErrorResponseMessage());
                softAssert.assertEquals(responseBodyCode, StatusCode.SERVER_ERROR_500.getValue());
            } else {
                log.warn(getQueryStatus());
            }
            softAssert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
            softAssert.assertAll(getInvalidResponseBodyMessage());
        } catch (InterruptedException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
