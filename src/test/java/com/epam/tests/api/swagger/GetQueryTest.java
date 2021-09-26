package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.model.request.User;
import com.epam.model.response.ResponseBody;
import com.epam.enums.StatusCode;
import com.epam.tests.api.swagger.conditions.GetQueryConditions;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;

@Slf4j
public class GetQueryTest extends GetQueryConditions {
    private final SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "dataForGetTest", dataProviderClass = DataProviderForTests.class)
    public void swaggerGetQueryTest(final User user, final int statusCode) {
        String userName = user.getUsername();
        log.info("Username: " + userName);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", BASE_URL, QUERY_END_POINT, userName)))
                                      .GET()
                                      .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == StatusCode.OK_200.getValue()) {
                User responseBodyUser = new Gson().fromJson(response.body(), User.class);
                softAssert.assertEquals(responseBodyUser, user);
            } else if (response.statusCode() == StatusCode.NOT_FOUND_404.getValue()) {
                ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
                softAssert.assertEquals(responseBody.getCode(), CODE_FROM_RESPONSE_BODY);
                softAssert.assertEquals(responseBody.getMessage(), MESSAGE_FROM_RESPONSE_BODY);
            } else {
                log.warn(QUERY_STATUS);
            }
            softAssert.assertEquals(response.statusCode(), statusCode, INVALID_STATUS_CODE_MESSAGE);
            softAssert.assertAll(INVALID_RESPONSE_BODY_MESSAGE);
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
