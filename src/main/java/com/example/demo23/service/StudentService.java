package com.example.demo23.service;

import com.example.demo23.entity.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> findStudent();
    Student findStudentById(Integer id);
    List<Student> deleteStudent(Integer id);
    Student updateStudent(Student student);
    void updateStudent(String name, String phone);
}
