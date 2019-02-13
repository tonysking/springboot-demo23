package com.example.demo23.repository;

import com.example.demo23.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentJpaSpecificationExecutorRepository
        extends JpaRepository<Student, Integer> , JpaSpecificationExecutor<Student> {
}
