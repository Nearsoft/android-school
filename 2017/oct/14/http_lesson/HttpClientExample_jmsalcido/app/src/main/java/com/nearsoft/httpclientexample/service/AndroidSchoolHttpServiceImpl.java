package com.nearsoft.httpclientexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.nearsoft.httpclientexample.model.Mentor;
import com.nearsoft.httpclientexample.model.Student;
import com.nearsoft.httpclientexample.transport.HttpExampleResponse;
import com.nearsoft.httpclientexample.transport.HttpTransport;

import java.util.List;

/**
 * Example of a simple service to go into the sample application on heroku and return values
 * Missing the Http Status validations and more, this is just an example.
 *
 * @author Jose Salcido
 */
public class AndroidSchoolHttpServiceImpl implements AndroidSchoolHttpService {

    private final HttpTransport httpTransport;
    private final static String ENDPOINT_BASE_URL = "http://androidschoolhttp.herokuapp.com";

    AndroidSchoolHttpServiceImpl(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    @Override
    public List<Mentor> getAllMentors() {
        String url = ENDPOINT_BASE_URL + "/mentors";
        HttpExampleResponse httpExampleResponse = doGet(url);
        TypeToken<List<Mentor>> typeToken = mentorListType();
        return gson().fromJson(httpExampleResponse.getBody(), typeToken.getType());
    }

    @Override
    public List<Student> getAllStudents() {
        String url = ENDPOINT_BASE_URL + "/students";
        HttpExampleResponse httpExampleResponse = doGet(url);
        TypeToken<List<Student>> typeToken = studentListType();
        return gson().fromJson(httpExampleResponse.getBody(), typeToken.getType());
    }

    @Override
    public Student getStudentById(int id) {
        String url = ENDPOINT_BASE_URL + "/students/" + id;
        HttpExampleResponse httpExampleResponse = doGet(url);
        return buildStudentFromJsonBody(httpExampleResponse.getBody());
    }

    private HttpExampleResponse doGet(String url) {
        return httpTransport.get(url);
    }

    @Override
    public Student addStudent(Student student) {
        String url = ENDPOINT_BASE_URL + "/students";
        HttpExampleResponse httpExampleResponse = httpTransport.post(url, gson().toJson(student));
        return buildStudentFromJsonBody(httpExampleResponse.getBody());
    }

    @Override
    public Student updateStudent(Student student) {
        String url = ENDPOINT_BASE_URL + "/students/" + student.getId();
        HttpExampleResponse httpExampleResponse = httpTransport.put(url, gson().toJson(student));
        return buildStudentFromJsonBody(httpExampleResponse.getBody());
    }

    @Override
    public boolean deleteStudent(Student student) {
        String url = ENDPOINT_BASE_URL + "/students/" + student.getId();
        HttpExampleResponse httpExampleResponse = httpTransport.delete(url);
        return httpExampleResponse.getStatusCode() == 200;
    }

    private Student buildStudentFromJsonBody(String body) {
        JsonParser jsonParser = new JsonParser();
        JsonObject studentObject =
                jsonParser.parse(body)
                        .getAsJsonObject()
                        .getAsJsonObject("student");
        return gson().fromJson(studentObject, Student.class);
    }

    private Gson gson() {
        return new GsonBuilder().create();
    }

    private TypeToken<List<Mentor>> mentorListType() {
        return new TypeToken<List<Mentor>>() {
        };
    }

    private TypeToken<List<Student>> studentListType() {
        return new TypeToken<List<Student>>() {
        };
    }
}
