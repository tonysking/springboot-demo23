package com.example.demo23.controller;

import com.example.demo23.entity.Student;
import com.example.demo23.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "学生接口", tags = {"CURD"})
@RequestMapping("stu")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @ApiOperation(value = "添加学生", notes = "添加学生接口")
    @PostMapping("/add")
    private Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);

    }
    @ApiOperation(value = "查询学生", notes = "查询学生接口")
    @GetMapping("/findAll")
    private List<Student> findStudent(){
        return studentService.findStudent();

    }
    @GetMapping("/delete/{id}")
    private List<Student> deleteStudent(@PathVariable("id") Integer id){
        return studentService.deleteStudent(id);

    }
    @PutMapping("/update")
    private Student updateStudent(Student student){
        return studentService.updateStudent(student);
    }

}
