package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.requestDto.StudentCreateRequest;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterf {
    Student save(StudentCreateRequest studentCreateRequest);
    Optional<Student> findById(int studentId);
    //    Student delete(int studentId);
    void delete(String email);

    Student updateBook(String email, StudentCreateRequest studentCreateRequest);

    Student findByEmail(String email);


    List<Book> getAllBooks(int studentId);
}
