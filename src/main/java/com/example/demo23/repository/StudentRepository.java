package com.example.demo23.repository;

import com.example.demo23.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer> {

    //按照jpa的语法规定写查询方法 会自动翻译为sql语句 不需要写实现
    List<Student> findStudentByStuAddress(String stuAddress);

    //自定义查询（对象是类）
    @Query("select s from Student s where id=(select max(id) from Student s1)")
    Student getStudentByMaxStuID();

    @Query("select o from Student o where o.stuAddress=?1 and o.stuPhone=?2")
    List<Student> queryByParams(String stuAddress, String stuPhone);
    @Query("select o from Student o where o.stuAddress=:stuAddress and o.stuPhone=:stuPhone")
    List<Student> queryByParams2(@Param("stuAddress") String address, @Param("stuPhone") String phone);

    @Query("select o from Student o where o.stuName like %?1%")
    List<Student> queryLike(String stuName);

    //原生态查询（对象是表）
    @Query(nativeQuery = true, value = "select count(1) from student")
    long getCount();
}
