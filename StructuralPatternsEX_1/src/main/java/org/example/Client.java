package org.example;

import java.io.IOException;

public class Client {
    public static void HttpGetRequest() throws IOException {
        HttpRequestFacade.HttpGetRequest();
    }

    public static void HttpPostRequest() throws IOException {
        HttpRequestFacade.HttpPostRequest("https://reqres.in/api/users");
    }

    public static void HttpDeleteRequest() throws IOException {
        HttpRequestFacade.HttpDeleteRequest();
    }

    public static void HttpPatchRequest() throws IOException {
        HttpRequestFacade.HttpPatchRequest();
    }


}
