package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepositoryInterf extends JpaRepository<MyUser,Integer> {
    MyUser findByEmail(String email);
}

