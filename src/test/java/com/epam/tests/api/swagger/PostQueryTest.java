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
import java.nio.file.Path;

import static java.lang.String.format;

public class PostQueryTest extends CommonConditions {
    private static final String QUERY_END_POINT = "/user/createWithList";
    private static final String HEADER_PARAM_NAME = "Content-Type";
    private static final String HEADER_PARAM_VALUE = "application/json";
    private static final String REQUEST_BODY_VALID = "post-request-body-valid.json";
    private static final String REQUEST_BODY_INVALID = "post-query-body-invalid.json";
    private static final String REQUEST_BODY_EMPTY = "post-query-body-empty.json";

    @Test(dataProvider = "dataForQuery")
    public void swaggerPostQueryTest(final String fileName, final int statusCode) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s", getBaseUrl(), QUERY_END_POINT)))
                .header(HEADER_PARAM_NAME, HEADER_PARAM_VALUE)
                .POST(HttpRequest.BodyPublishers.ofFile(
                    Path.of(format(getRequestBodyPath(), fileName))))
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
            {REQUEST_BODY_INVALID, StatusCode.SERVER_ERROR_500.getValue()},
            {REQUEST_BODY_EMPTY, StatusCode.SERVER_ERROR_500.getValue()},
            {REQUEST_BODY_VALID, StatusCode.OK_200.getValue()}
        };
    }
}
