package com.example.demo23.serviceImpl;

import com.example.demo23.entity.Student;
import com.example.demo23.repository.StudentRepository;
import com.example.demo23.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceIml implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).get();
    }


    @Override
    public List<Student> deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Modifying
    //@Query("update student u set u.stuName = :stuName where u.stuPhone = :stuPhone"),@Param("stuName") String stuName
    public Student updateStudent(Integer id) {
        Student student = studentRepository.findById(id).get();
        student.setStuName("ye");
        return studentRepository.save(student);
    }
}
