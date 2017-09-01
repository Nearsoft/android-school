package com.nearsoft.httpclientexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.httpclientexample.model.Mentor;
import com.nearsoft.httpclientexample.model.Student;
import com.nearsoft.httpclientexample.transport.HttpTransport;
import com.nearsoft.httpclientexample.transport.HttpUrlConnectionTransport;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AndroidSchoolHttpServiceImplIntegrationTest {

    @Test
    public void it_getAllMentors() throws Exception {
        System.out.println("This should be executed in a different task, but ok for this example");

        HttpTransport httpTransport =
                new HttpUrlConnectionTransport();

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        List<Mentor> allMentors = androidSchoolHttpService.getAllMentors();
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(allMentors));

        assertThat("allMentors is not empty", allMentors.size(), is(not(0)));
        assertThat("allMentors is not empty", allMentors.size(), is(equalTo(6)));
        assertThat(allMentors.get(0).getId(), is(equalTo(1)));
    }

    @Test
    public void it_allStudentsFlow() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("This should be executed in a different task, but ok for this example");

        HttpTransport httpTransport =
                new HttpUrlConnectionTransport();

        AndroidSchoolHttpServiceImpl androidSchoolHttpService =
                new AndroidSchoolHttpServiceImpl(httpTransport);

        Student studentToAdd = new Student();
        studentToAdd.setName("Test Testson");
        studentToAdd.setTwitterHandle("@mr_test");
        studentToAdd.setEmail("test@email.com");
        studentToAdd.setSchool("ITTEST");

        Student studentFromBackend = androidSchoolHttpService.addStudent(studentToAdd);
        System.out.println("Student added:");
        System.out.println(gson.toJson(studentFromBackend));

        assertThat(studentFromBackend.getId(), is(notNullValue()));
        assertThat(studentFromBackend.getName(), is(equalTo(studentToAdd.getName())));
        assertThat(studentFromBackend.getEmail(), is(equalTo(studentToAdd.getEmail())));
        assertThat(studentFromBackend.getSchool(), is(equalTo(studentToAdd.getSchool())));

        System.out.println("Updating student name to " + studentFromBackend.getName() + " - updated");
        String nameToSend = studentFromBackend.getName() + " - updated";
        studentFromBackend.setName(nameToSend);

        Student updatedStudent = androidSchoolHttpService.updateStudent(studentFromBackend);
        System.out.println(studentFromBackend);
        System.out.println(updatedStudent);

        assertThat(studentFromBackend.getName(), is(equalTo(nameToSend)));
        assertThat(updatedStudent.getName(), is(equalTo(nameToSend)));

        System.out.println("Deleting student with id: " + updatedStudent.getId());
        boolean deleted = androidSchoolHttpService.deleteStudent(updatedStudent);
        assertThat(deleted, is(equalTo(true)));

        System.out.println("All students from backend");
        List<Student> allStudents = androidSchoolHttpService.getAllStudents();

        System.out.println(allStudents);
        assertThat(!allStudents.contains(updatedStudent), is(equalTo(true)));
    }

}