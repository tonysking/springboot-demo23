package com.example.demo23.controller;

import com.example.demo23.entity.Student;
import com.example.demo23.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "学生网页接口", tags = {"学生网页CURD"})
@RequestMapping("web")
public class StudentPageController {

    @Autowired
    private StudentService studentService;

    //查询全部
    @ApiOperation(value = "查询全部学生", notes = "查询全部学生接口")
    @GetMapping("/all")
    public String getAllStu(Model model) {
        // 查询用户
        List<Student> stus = this.studentService.findStudent();
        // 放入模型
        model.addAttribute("stus", stus);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "index";
    }

    //点击新增转向新增页面
    @GetMapping("/toAdd")
    private String toAdd(Model model){
        return "add";
    }
    //点击"确认新增"完成新增并返回首页
    @PostMapping("/add")
    private String addStudent(Student student){
        studentService.addStudent(student);
        return "redirect:/web/all";
    }

    //删除
    @GetMapping("/delete/{id}")
    private String deleteStudentPage(@PathVariable("id") Integer id){
        studentService.deleteStudent(id);
        return "redirect:/web/all";

    }

    //点击修改转向修改页面
    @GetMapping("toUpdate")
    private  String toUpdate(Model model,Integer id){
        Student studentById = studentService.findStudentById(id);
        model.addAttribute("stu",studentById);
        return "update";
    }
    //点击"提交"完成修改并返回首页
    @PostMapping("/update")
    private String updateStudent(Student student){

        studentService.updateStudent(student);
        return "redirect:/web/all";
    }
}
