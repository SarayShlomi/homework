package org.example;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        Client.HttpGetRequest();
        Client.HttpPostRequest();
        Client.HttpDeleteRequest();
        Client.HttpPatchRequest();
    }
}