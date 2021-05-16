package com.epam.tests.api.swagger;

import com.epam.data.User;
import com.epam.data.UserFactory;
import com.epam.enums.StatusCode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.epam.data.DataConverter.objectToJsonConvert;
import static java.lang.String.format;

public class PostQueryTestWithFactory extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/createWithList";
    private static final String HEADER_PARAM_NAME = "Content-Type";
    private static final String HEADER_PARAM_VALUE = "application/json";

    @Test(dataProvider = "dataForQuery")
    public void swaggerPostQueryTestWithFactory(final String userType, final int statusCode) {
        User user = new UserFactory().getUser(userType);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(format("%s%s", getBaseUrl(), QUERY_END_POINT)))
                    .header(HEADER_PARAM_NAME, HEADER_PARAM_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(
                            objectToJsonConvert(user)))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
        } catch (InterruptedException | IOException e) {
            getLogger().error(e.getMessage());
        }
    }

    @DataProvider
    public Object[][] dataForQuery() {
        return new Object[][]{
                {"validUser", StatusCode.OK_200.getValue()},
                {"invalidUser", StatusCode.SERVER_ERROR_500.getValue()},
                {"emptyUser", StatusCode.SERVER_ERROR_500.getValue()}
        };
    }
}
