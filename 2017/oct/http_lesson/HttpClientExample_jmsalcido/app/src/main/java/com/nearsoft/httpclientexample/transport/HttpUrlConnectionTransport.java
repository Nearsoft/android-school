package com.nearsoft.httpclientexample.transport;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jmsalcido on 9/1/17.
 */
public class HttpUrlConnectionTransport implements HttpTransport {
    @Override
    public HttpExampleResponse get(String url) {
        return sendRequest("GET", url, null);
    }

    @Override
    public HttpExampleResponse post(String url, String jsonData) {
        return sendRequest("POST", url, jsonData);
    }

    @Override
    public HttpExampleResponse put(String url, String jsonData) {
        return sendRequest("PUT", url, jsonData);
    }

    @Override
    public HttpExampleResponse delete(String url) {
        return sendRequest("DELETE", url, null);
    }

    private HttpExampleResponse sendRequest(String method, String urlString, String requestData) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);

            if (requestData != null && !requestData.isEmpty()) {
                setRequestBody(urlConnection, requestData);
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return new HttpExampleResponse(urlConnection.getResponseCode(), readStream(in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRequestBody(HttpURLConnection connection, String data) throws IOException {
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        bufferedWriter.write(data);
        bufferedWriter.flush();
    }

    private String readStream(InputStream stream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }
}
