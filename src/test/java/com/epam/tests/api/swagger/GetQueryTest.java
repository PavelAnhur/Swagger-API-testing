package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.data.request.User;
import com.epam.data.response.ResponseBody;
import com.epam.tests.api.swagger.conditions.GetQueryConditions;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
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

    @Test(dataProvider = "dataForGetTest", dataProviderClass = DataProviderForTests.class)
    public void swaggerGetQueryTest(final User user, final int statusCode) {
        String userName = user.getUsername();
        log.info("Username: " + userName);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), getQueryEndPoint(), userName)))
                                      .GET()
                                      .build();

        SoftAssert softAssert = new SoftAssert();
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.SC_OK) {
                User responseBodyUser = new Gson().fromJson(response.body(), User.class);
                softAssert.assertEquals(responseBodyUser, user);
            } else if (response.statusCode() == HttpStatus.SC_NOT_FOUND) {
                ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
                softAssert.assertEquals(responseBody.getCode(), getCodeFromResponseBody());
                softAssert.assertEquals(responseBody.getMessage(), getMessageFromResponseBody());
            } else {
                log.warn(getQueryStatus());
            }
            softAssert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
            softAssert.assertAll(getInvalidResponseBodyMessage());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
