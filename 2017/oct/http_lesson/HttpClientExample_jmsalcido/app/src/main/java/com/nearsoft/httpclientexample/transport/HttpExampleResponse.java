package com.nearsoft.httpclientexample.transport;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * This class represents a simple response from Http
 */
public class HttpExampleResponse {

    private final HttpResponse response;
    private final int statusCode;
    private String body;

    HttpExampleResponse(HttpResponse response) throws IOException {
        this.response = response;
        this.statusCode = response.getStatusLine().getStatusCode();
        this.body = EntityUtils.toString(response.getEntity());
    }

    public HttpResponse getResponse() {
        return response;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
