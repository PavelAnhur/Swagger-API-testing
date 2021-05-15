package com.epam.tests.api.swagger;

import com.epam.enums.StatusCode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;

public class DeleteQueryTest extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/";

    @Test(dataProvider = "deleteQueryData")
    public void swaggerDeleteQueryTest(final String userName, final int statusCode) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), QUERY_END_POINT, userName)))
                                      .DELETE()
                                      .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
        } catch (IOException | InterruptedException e) {
            getLogger().error(e.getMessage());
        }
    }

    @DataProvider
    public Object[][] deleteQueryData() {
        return new Object[][]{
                {"pavelanhur", StatusCode.OK_200.getValue()},
                {"asdfasdf", StatusCode.NOT_FOUND_404.getValue()}
        };
    }
}
