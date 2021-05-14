package com.epam.api;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class SwaggerAPITests {

    @Test
    public void swaggerPostRequestTest() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://petstore.swagger.io/v2/user/createWithList"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(
                            Path.of("src/test/resources/request_body/post-request-body.json")))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Headers: " + response.headers());
            String responseBody = response.body();
            System.out.println("Response body: " + responseBody);
            int statusCode = response.statusCode();
            System.out.println("Status code: " + statusCode);
            HttpClient.Version ver = response.version();
            System.out.println("Version: " + ver);
            URI uri = response.uri();
            System.out.println("URI: " + uri);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
