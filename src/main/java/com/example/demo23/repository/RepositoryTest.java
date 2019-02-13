package com.example.demo23.repository;

import com.example.demo23.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private  StudentRepository studentRepository;

    //测试jpa接口方法
    @Test
    public void test1(){
        List<Student> studentByStuAddress = studentRepository.findStudentByStuAddress("213");
        for (Student s:studentByStuAddress) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void test2(){
        Student studentByMaxStuID = studentRepository.getStudentByMaxStuID();
        System.out.println(studentByMaxStuID.toString());
    }

    @Test
    public void test3(){
        List<Student> students = studentRepository.queryByParams("213", "1234");
        for (Student s:students) {
            System.out.println(s.toString());
        }

        List<Student> students2 = studentRepository.queryByParams2("213", "1234");
        for (Student s:students2) {
            System.out.println(s.toString());
        }

    }

    @Test
    public void test4(){
        List<Student> nameLike = studentRepository.queryLike("ny");
        for (Student s:nameLike) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void test5(){
        long count = studentRepository.getCount();
        System.out.println("count:"+count);
    }

}
