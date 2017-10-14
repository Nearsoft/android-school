package com.nearsoft.httpclientexample.model;

import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jmsalcido on 8/31/17.
 */
public class MentorTest {

    @Test
    public void gsonSerialize() {
        Mentor mentor = new GsonBuilder().create().fromJson(stubMentorJson(), Mentor.class);
        assertThat(mentor.getId(), is(equalTo(1)));
        assertThat(mentor.getName(), is(equalTo("Jose Salcido")));
        assertThat(mentor.getPosition(), is(equalTo("Software Engineer")));
        assertThat(mentor.getEmail(), is(equalTo("jsalcido@nearsoft.com")));
        assertThat(mentor.getLocation(), is(equalTo("HMO")));
    }

    private String stubMentorJson() {
        return "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Jose Salcido\",\n" +
                "        \"position\": \"Software Engineer\",\n" +
                "        \"email\": \"jsalcido@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.300Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.300Z\"\n" +
                "    }";
    }
}