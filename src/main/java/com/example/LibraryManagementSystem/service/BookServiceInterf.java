package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.requestDto.BookCreateRequest;
import com.example.LibraryManagementSystem.responseDto.BookSearchResponse;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface BookServiceInterf  {
    Book create(BookCreateRequest bookCreateRequest) throws DataIntegrityViolationException;

    List<BookSearchResponse> findFilteredBooks(BookFilterType bookFilterType, String value);
    Book findById(int bookId);
    Book save(Book book);
    void delete(Book book);

    Book updateBook(int bookId,BookCreateRequest bookCreateRequest)throws DataIntegrityViolationException;



}
