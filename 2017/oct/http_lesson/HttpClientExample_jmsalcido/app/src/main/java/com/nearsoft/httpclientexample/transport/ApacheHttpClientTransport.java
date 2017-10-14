package com.nearsoft.httpclientexample.transport;

import android.net.http.Headers;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApacheHttpClientTransport implements HttpTransport {

    private final HttpClient httpClient;

    public ApacheHttpClientTransport(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpExampleResponse get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return sendRequest(httpGet);
    }

    @Override
    public HttpExampleResponse post(String url, String jsonData) {
        HttpPost httpPost = new HttpPost(url);
        return sendRequest(httpPost, jsonData);
    }

    @Override
    public HttpExampleResponse put(String url, String jsonData) {
        HttpPut httpPut = new HttpPut(url);
        return sendRequest(httpPut, jsonData);
    }

    @Override
    public HttpExampleResponse delete(String url) {
        HttpDelete httpDelete = new HttpDelete(url);
        return sendRequest(httpDelete);
    }

    private HttpExampleResponse sendRequest(HttpEntityEnclosingRequestBase request, String data) {
        try {
            request.setEntity(new StringEntity(data));
            request.addHeader(Headers.CONTENT_TYPE, "application/json");
            return sendRequest(request);
        } catch (UnsupportedEncodingException e) {
            logException(e);

            throw new RuntimeException(e);
        }
    }

    private HttpExampleResponse sendRequest(HttpRequestBase request) {
        try {
            Log.i("HTTPClientRequest", request.toString());

            HttpResponse clientResponse = httpClient.execute(request);
            int statusCode = clientResponse.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(clientResponse.getEntity());
            HttpExampleResponse response = new HttpExampleResponse(statusCode, body);

            Log.i("HTTPClientResponse", response.toString());

            return response;
        } catch (IOException e) {
            logException(e);

            throw new RuntimeException(e);
        }
    }

    private void logException(Exception e) {
        Log.e(getClass().getSimpleName(), e.getMessage(), e);
    }

}
