package com.example.demo23.repository;

import com.example.demo23.entity.Student;
import com.example.demo23.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private  StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentJpaSpecificationExecutorRepository studentJpaSpecificationExecutorRepository;

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

    @Test
    public void test6(){
        studentService.updateStudent("huhu", "111");
    }


    //分页和排序----PagingAndSortingRepository
    @Test
    public void testPageAndSort(){

        //按照stuID降序排列
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "stuID");
        Sort sort = new Sort(order);

        //page从0开始 每页size为5
        Pageable pageable = new PageRequest(0,5,sort);
        Page<Student> allPage = studentRepository.findAll(pageable);
        System.out.println("查询总页数："+allPage.getTotalPages());
        System.out.println("查询总记录数："+allPage.getTotalElements());
        System.out.println("查询当前页数："+(allPage.getNumber()+1));
        System.out.println("查询当前页面记录数："+allPage.getNumberOfElements());
        System.out.println("查询当前页面集合："+allPage.getContent());

    }

    //分页、排序、查询条件
    @Test
    public void testSpecification(){

        //按照stuID降序排列
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "stuID");
        Sort sort = new Sort(order);

        //page从0开始 每页size为5
        Pageable pageable = new PageRequest(0,5,sort);

        //条件：id>30
        /**
         * root: 查询的类型（student）
         * query：添加查询条件
         * cb: 构建Predicate
         */
        Specification<Student> studentSpecification = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {

                Path stuIDPath = root.get("stuID");
                return criteriaBuilder.gt(stuIDPath,30);
            }
        };


        Page<Student> allPage = studentJpaSpecificationExecutorRepository.findAll(studentSpecification, pageable);
        System.out.println("查询总页数："+allPage.getTotalPages());
        System.out.println("查询总记录数："+allPage.getTotalElements());
        System.out.println("查询当前页数："+(allPage.getNumber()+1));
        System.out.println("查询当前页面记录数："+allPage.getNumberOfElements());
        System.out.println("查询当前页面集合："+allPage.getContent());

    }

}
