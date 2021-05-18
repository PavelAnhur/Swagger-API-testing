package com.epam.tests.api.swagger;

import com.epam.data.provider.DataProviderForTests;
import com.epam.tests.api.swagger.conditions.DeleteQueryConditions;
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
public class DeleteQueryTest extends DeleteQueryConditions {

    @Test(dataProvider = "dataForDeleteTest", dataProviderClass = DataProviderForTests.class)
    public void swaggerDeleteQueryTest(final String userName, final int statusCode) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), getQueryEndPoint(), userName)))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
