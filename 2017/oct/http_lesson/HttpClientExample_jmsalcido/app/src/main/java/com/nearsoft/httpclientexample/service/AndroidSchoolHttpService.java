package com.nearsoft.httpclientexample.service;

import com.nearsoft.httpclientexample.model.Mentor;
import com.nearsoft.httpclientexample.model.Student;

import java.util.List;

/**
 * Created by jmsalcido on 8/31/17.
 */
public interface AndroidSchoolHttpService {


    List<Mentor> getAllMentors();

    List<Student> getAllStudents();

    Student getStudentById(int id);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    boolean deleteStudent(Student student);

}
