package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryInterf  extends JpaRepository<Student,Integer> {


    Student findByEmail(String email);
    void deleteByEmail(String email);
}
