package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.requestDto.AuthorCreateRequest;

public interface AuthorServiceInterf {
    Author findByEmail(String email);

    Author createAuthor(AuthorCreateRequest authorCreateRequest);

    void delete(Author author);

    Author updateAuthor(String email,AuthorCreateRequest authorCreateRequest);
}