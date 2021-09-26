package com.epam.tests.api.swagger;

import com.epam.model.request.User;
import com.epam.data.provider.DataProviderForTests;
import com.epam.model.response.ResponseBody;
import com.epam.enums.StatusCode;
import com.epam.tests.api.swagger.conditions.DeleteQueryConditions;
import com.epam.utils.JsonUtils;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
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
public class DeleteQueryTest extends DeleteQueryConditions {
    private final SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "dataForDeleteTest", dataProviderClass = DataProviderForTests.class)
    public void swaggerDeleteQueryTest(final User user, final int statusCode) {
        String userAsJson = JsonUtils.toJson(user);
        String userName = JsonPath.parse(userAsJson).read("$.username").toString();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", BASE_URL, QUERY_END_POINT, userName)))
                                      .DELETE()
                                      .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ResponseBody responseBody = new Gson().fromJson(response.body(), ResponseBody.class);
            if (response.statusCode() == StatusCode.OK_200.getValue()) {
                softAssert.assertEquals(responseBody.getCode(), StatusCode.OK_200.getValue());
                softAssert.assertEquals(responseBody.getMessage(), userName);
            } else if (response.statusCode() == StatusCode.NOT_FOUND_404.getValue()) {
                softAssert.assertEquals(responseBody, null);
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
