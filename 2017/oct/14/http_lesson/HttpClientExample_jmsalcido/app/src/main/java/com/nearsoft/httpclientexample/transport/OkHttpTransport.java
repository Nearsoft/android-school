package com.nearsoft.httpclientexample.transport;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jmsalcido on 9/6/17.
 */

public class OkHttpTransport implements HttpTransport {

    private final OkHttpClient okHttpClient;

    public OkHttpTransport(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public HttpExampleResponse get(String url) {
        Request request = new Request.Builder().get().url(url).build();
        return executeRequest(request);
    }

    private HttpExampleResponse executeRequest(Request request) {
        try {
            Response response = okHttpClient.newCall(request).execute();
            //noinspection ConstantConditions // should contain value always.
            return new HttpExampleResponse(response.code(), response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpExampleResponse post(String url, String jsonData) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonData);
        Request request = new Request.Builder().post(requestBody).url(url).build();
        return executeRequest(request);
    }

    @Override
    public HttpExampleResponse put(String url, String jsonData) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonData);
        Request request = new Request.Builder().put(requestBody).url(url).build();
        return executeRequest(request);
    }

    @Override
    public HttpExampleResponse delete(String url) {
        Request request = new Request.Builder().delete().url(url).build();
        return executeRequest(request);
    }
}
