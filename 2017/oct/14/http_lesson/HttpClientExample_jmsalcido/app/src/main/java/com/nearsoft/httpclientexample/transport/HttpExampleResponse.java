package com.nearsoft.httpclientexample.transport;


/**
 * This class represents a simple response from Http
 */
public class HttpExampleResponse {

    private final int statusCode;
    private String body;

    HttpExampleResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
