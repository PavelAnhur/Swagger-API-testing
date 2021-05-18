package com.epam.tests.api.swagger;

import com.epam.data.provider.GetQueryData;
import com.epam.tests.api.swagger.conditions.GetQueryConditions;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.lang.String.format;

@Slf4j
public class GetQueryTest extends GetQueryConditions {

    @Test(dataProvider = "dataForTest", dataProviderClass = GetQueryData.class)
    public void swaggerGetQueryTest(final String userName, final int statusCode, final String responseFile) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(format("%s%s%s", getBaseUrl(), getQueryEndPoint(), userName)))
                .GET()
                .build();
        try {
            File file = new File(format("%s/%s", getResponseBodyPath(), responseFile));
            boolean isDeleted;
            if (file.exists()) {
                isDeleted = file.delete();
                log.debug("{} file is deleted: {}", file.getName(), isDeleted);
            } else {
                log.debug("{} file doesn't exist", file.getName());
            }
            HttpResponse<Path> response =
                    client.send(request, HttpResponse.BodyHandlers.ofFile(
                            Path.of(getResponseBodyPath(), responseFile)));

            Assert.assertEquals(response.statusCode(), statusCode, getInvalidStatusCodeMessage());

        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
