package com.nearsoft.httpclientexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.httpclientexample.model.Mentor;
import com.nearsoft.httpclientexample.model.Student;
import com.nearsoft.httpclientexample.transport.HttpExampleResponse;
import com.nearsoft.httpclientexample.transport.HttpTransport;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jmsalcido on 8/31/17.
 */
public class AndroidSchoolHttpServiceImplTest {
    @Test
    public void testGetAllMentors() throws Exception {
        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn(stubMentorListBody());

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.get("http://androidschoolhttp.herokuapp.com/mentors"))
                .thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Mentor> allMentors = androidSchoolHttpService.getAllMentors();

        assertThat("allMentors is not empty", allMentors.size(), is(not(0)));
        assertThat("allMentors size is equal to 6",
                allMentors.size(), is(equalTo(6)));
    }

    @Test
    public void testGetAllMentors_emptyList() throws Exception {
        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn("[]");

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.get("http://androidschoolhttp.herokuapp.com/mentors"))
                .thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Mentor> allMentors = androidSchoolHttpService.getAllMentors();

        assertThat("allMentors is empty",
                allMentors.isEmpty(), is(equalTo(true)));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn(stubStudentListBody());

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.get("http://androidschoolhttp.herokuapp.com/students"))
                .thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Student> allStudents = androidSchoolHttpService.getAllStudents();

        assertThat("allStudents is not empty",
                allStudents.size(), is(not(0)));

        assertThat("allStudents is equal to 1",
                allStudents.size(), is(equalTo(1)));
    }

    @Test
    public void testGetAllStudents_emptyList() throws Exception {
        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn("[]");

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.get("http://androidschoolhttp.herokuapp.com/students"))
                .thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Student> allStudents = androidSchoolHttpService.getAllStudents();

        assertThat("allStudents is empty",
                allStudents.isEmpty(), is(equalTo(true)));
    }

    @Test
    public void testGetStudentById() throws Exception {
        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn(stubStudentBody());

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.get("http://androidschoolhttp.herokuapp.com/students/1"))
                .thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        Student student = androidSchoolHttpService.getStudentById(1);

        assertThat(student, is(notNullValue()));
        assertThat(student.getName(), is(equalTo("Jose Salcido")));
    }

    @Test
    public void testAddStudent() throws Exception {
        Student requestStudent = new Student();
        requestStudent.setName("Test Student");
        requestStudent.setSchool("Test School");
        requestStudent.setEmail("Test Email");
        requestStudent.setTwitterHandle("Test Twitter Handle");

        Map<String, Student> responseStudentMap = new HashMap<>();
        responseStudentMap.put("student", requestStudent);

        Gson gson = new GsonBuilder().create();
        String requestJson = gson.toJson(requestStudent);
        String responseJson = gson.toJson(responseStudentMap);

        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn(responseJson);

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.post(
                "http://androidschoolhttp.herokuapp.com/students", requestJson)
        ).thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        Student responseStudent = androidSchoolHttpService.addStudent(requestStudent);

        assertThat(responseStudent, is(notNullValue()));
        assertThat(responseStudent, is(equalTo(requestStudent)));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student requestStudent = new Student();
        requestStudent.setId(100);
        requestStudent.setName("Test Student");
        requestStudent.setSchool("Test School");
        requestStudent.setEmail("Test Email");
        requestStudent.setTwitterHandle("Test Twitter Handle");

        Map<String, Student> responseStudentMap = new HashMap<>();
        responseStudentMap.put("student", requestStudent);

        Gson gson = new GsonBuilder().create();
        String requestJson = gson.toJson(requestStudent);
        String responseJson = gson.toJson(responseStudentMap);

        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getBody()).thenReturn(responseJson);

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.put(
                "http://androidschoolhttp.herokuapp.com/students/100", requestJson)
        ).thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        Student responseStudent = androidSchoolHttpService.updateStudent(requestStudent);

        assertThat(responseStudent, is(notNullValue()));
        assertThat(responseStudent, is(equalTo(requestStudent)));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student requestStudent = new Student();
        requestStudent.setId(100);

        HttpExampleResponse response = mock(HttpExampleResponse.class);
        when(response.getStatusCode()).thenReturn(200);

        HttpTransport httpTransport = mock(HttpTransport.class);
        when(httpTransport.delete(
                "http://androidschoolhttp.herokuapp.com/students/100"
        )).thenReturn(response);

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        boolean value = androidSchoolHttpService.deleteStudent(requestStudent);

        assertThat(value, is(equalTo(true)));
    }

    private String stubStudentBody() {
        return "{\n" +
                "    \"student\": {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Jose Salcido\",\n" +
                "        \"school\": \"ITSON\",\n" +
                "        \"twitter_handle\": \"@jmsalcido\",\n" +
                "        \"email\": \"jsalcido@nearsoft.com\",\n" +
                "        \"created_at\": \"2017-09-01T00:02:10.394Z\",\n" +
                "        \"updated_at\": \"2017-09-01T00:02:10.394Z\"\n" +
                "    }\n" +
                "}";
    }

    private String stubStudentListBody() {
        return "[\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Jose Salcido\",\n" +
                "        \"school\": \"ITSON\",\n" +
                "        \"twitter_handle\": \"@jmsalcido\",\n" +
                "        \"email\": \"jsalcido@nearsoft.com\",\n" +
                "        \"created_at\": \"2017-09-01T00:02:10.394Z\",\n" +
                "        \"updated_at\": \"2017-09-01T00:02:10.394Z\"\n" +
                "    }\n" +
                "]";
    }

    private String stubMentorListBody() {
        return "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Jose Salcido\",\n" +
                "        \"position\": \"Software Engineer\",\n" +
                "        \"email\": \"jsalcido@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.300Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.300Z\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Ivan Esparza\",\n" +
                "        \"position\": \"Software Developer\",\n" +
                "        \"email\": \"iesparza@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.308Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.308Z\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Ramces Baniares\",\n" +
                "        \"position\": \"Software Developer\",\n" +
                "        \"email\": \"rbanares@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.324Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.324Z\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"Carlos Cuevas\",\n" +
                "        \"position\": \"Software Developer\",\n" +
                "        \"email\": \"ccuevas@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.334Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.334Z\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Mike Hurtado\",\n" +
                "        \"position\": \"Software Developer\",\n" +
                "        \"email\": \"mhurtado@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.341Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.341Z\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"Ramon Raya\",\n" +
                "        \"position\": \"Software Developer\",\n" +
                "        \"email\": \"rraya@nearsoft.com\",\n" +
                "        \"location\": \"HMO\",\n" +
                "        \"created_at\": \"2017-08-31T02:56:48.357Z\",\n" +
                "        \"updated_at\": \"2017-08-31T02:56:48.357Z\"\n" +
                "    }\n" +
                "]";
    }

}