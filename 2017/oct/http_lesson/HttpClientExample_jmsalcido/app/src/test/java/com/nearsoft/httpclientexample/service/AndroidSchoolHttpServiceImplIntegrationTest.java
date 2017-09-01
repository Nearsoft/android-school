package com.nearsoft.httpclientexample.service;

import com.google.gson.GsonBuilder;
import com.nearsoft.httpclientexample.model.Mentor;
import com.nearsoft.httpclientexample.transport.ApacheHttpClientTransport;
import com.nearsoft.httpclientexample.transport.HttpExampleResponse;
import com.nearsoft.httpclientexample.transport.HttpTransport;

import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AndroidSchoolHttpServiceImplIntegrationTest {

    @Test
    public void it_getAllMentors() throws Exception {
        System.out.println("This should be executed in a different task, but ok for this example");

        HttpTransport httpTransport =
                new ApacheHttpClientTransport(HttpClientBuilder.create().build());

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Mentor> allMentors = androidSchoolHttpService.getAllMentors();
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(allMentors));

        assertThat("allMentors is not empty", allMentors.size(), is(not(0)));
        assertThat("allMentors is not empty", allMentors.size(), is(equalTo(6)));
        assertThat(allMentors.get(0).getId(), is(equalTo(1)));
    }

}