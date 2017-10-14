package com.nearsoft.httpclientexample.transport;

public interface HttpTransport {

    HttpExampleResponse get(String url);

    HttpExampleResponse post(String url, String jsonData);

    HttpExampleResponse put(String url, String jsonData);

    HttpExampleResponse delete(String url);

}
