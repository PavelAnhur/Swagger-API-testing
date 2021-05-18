package com.epam.tests.api.swagger;

import com.epam.data.Users;
import com.epam.data.provider.DataProviderForTests;
import com.epam.tests.api.swagger.conditions.PostQueryConditions;
import com.epam.utils.DataConverter;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.format;

@Slf4j
public class PostQueryTest extends PostQueryConditions {

    @Test(dataProvider = "dataForPostQuery", dataProviderClass = DataProviderForTests.class)
    public void swaggerPostQueryTest(final Users users, final int statusCode) {
        String usersAsJson = new DataConverter().convertObjectToJson(users);
        String requestBody = JsonPath.parse(usersAsJson).read("$.users").toString();
        System.out.println("Request body: " + requestBody);
        log.info("Request body: " + requestBody);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(format("%s%s", getBaseUrl(), getQueryEndPoint())))
                    .header(getHeaderParamName(), getHeaderParamValue())
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
        } catch (InterruptedException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
