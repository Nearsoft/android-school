package com.nearsoft.httpclientexample.model;

import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jmsalcido on 8/31/17.
 */
public class StudentTest {

    @Test
    public void testGsonDeserialization() throws Exception {
        Student student = new GsonBuilder().create().fromJson(stubJson(), Student.class);
        assertThat(student.getId(), is(equalTo(2)));
        assertThat(student.getName(), is(equalTo("Jose Salcido")));
        assertThat(student.getSchool(), is(equalTo("ITSON")));
        assertThat(student.getTwitterHandle(), is(equalTo("@jmsalcido")));
        assertThat(student.getEmail(), is(equalTo("jsalcido@nearsoft.com")));
    }

    private String stubJson() {
        return "{\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Jose Salcido\",\n" +
                "        \"school\": \"ITSON\",\n" +
                "        \"twitter_handle\": \"@jmsalcido\",\n" +
                "        \"email\": \"jsalcido@nearsoft.com\",\n" +
                "        \"created_at\": \"2017-09-01T00:02:10.394Z\",\n" +
                "        \"updated_at\": \"2017-09-01T00:02:10.394Z\"\n" +
                "    }";
    }
}