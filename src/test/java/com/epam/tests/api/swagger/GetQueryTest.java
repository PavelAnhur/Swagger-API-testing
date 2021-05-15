package com.epam.tests.api.swagger;

import com.epam.enums.StatusCode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.lang.String.format;

public class GetQueryTest extends CommonConditions {

    private static final String QUERY_END_POINT = "/user/";
    private static final String RESPONSE_BODY_FILE_VALID = "get-query-valid.json";
    private static final String RESPONSE_BODY_FILE_INVALID = "get-query-invalid.json";


    @Test(dataProvider = "getQueryData")
    public void swaggerGetQueryTest(final String userName, final int statusCode, final String responseFile) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), QUERY_END_POINT, userName)))
                                      .GET()
                                      .build();
        try {
            File file = new File(String.format("%s/%s", getResponseBodyPath(), responseFile));
            boolean isDeleted;
            if (file.exists()) {
                isDeleted = file.delete();
                getLogger().debug("{} file is deleted: {}", file.getName(), isDeleted);
            } else {
                getLogger().debug("{} file doesn't exist", file.getName());
            }
            HttpResponse<Path> response =
                    client.send(request, HttpResponse.BodyHandlers.ofFile(
                            Path.of(getResponseBodyPath(), responseFile)));

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());

        } catch (IOException | InterruptedException e) {
            getLogger().error(e.getMessage());
        }
    }

    @DataProvider
    public Object[][] getQueryData() {
        return new Object[][]{
                {"pavelanhur", StatusCode.OK_200.getValue(), RESPONSE_BODY_FILE_VALID},
                {"asdfasdf", StatusCode.NOT_FOUND_404.getValue(), RESPONSE_BODY_FILE_INVALID}
        };
    }
}
