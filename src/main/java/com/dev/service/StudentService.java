package com.dev.service;

import com.dev.entity.Student;
import com.dev.exception.StudentAlreadyExistsException;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws StudentAlreadyExistsException;
    List<Student> getAllStudent();
    Student getStudentById(Long id);
    Student updateStudent(Student student,Long id);
    String deleteStudent(Long id);
}
