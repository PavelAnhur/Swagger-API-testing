package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.data.request.User;
import com.epam.data.response.ResponseBody;
import com.epam.tests.api.swagger.conditions.DeleteQueryConditions;
import com.epam.utils.JsonUtils;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
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
public class DeleteQueryTest extends DeleteQueryConditions {

    @Test(dataProvider = "dataForDeleteTest", dataProviderClass = DataProviderForTests.class)
    public void swaggerDeleteQueryTest(final User user, final int statusCode) {
        String userAsJson = JsonUtils.toJson(user);
        String userName = JsonPath.parse(userAsJson).read("$.username").toString();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), getQueryEndPoint(), userName)))
                                      .DELETE()
                                      .build();

        SoftAssert softAssert = new SoftAssert();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
            if (response.statusCode() == HttpStatus.SC_OK) {
                softAssert.assertEquals(responseBody.getCode(), HttpStatus.SC_OK);
                softAssert.assertEquals(responseBody.getMessage(), userName);
            } else if (response.statusCode() == HttpStatus.SC_NOT_FOUND) {
                softAssert.assertEquals(responseBody, null);
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
