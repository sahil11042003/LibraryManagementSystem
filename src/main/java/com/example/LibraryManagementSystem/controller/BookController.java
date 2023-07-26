package com.example.LibraryManagementSystem.controller;


import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.exception.BookException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.requestDto.BookCreateRequest;
import com.example.LibraryManagementSystem.responseDto.BookSearchResponse;
import com.example.LibraryManagementSystem.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {
    @Autowired
    BookServiceInterf bookServiceInterf;

    @PostMapping("/create/book")
    public ResponseEntity createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        try {
            bookServiceInterf.create(bookCreateRequest);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity("Duplicate value on unique fields", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Book Saved Successfully",HttpStatus.CREATED);
    }



    @GetMapping("/search/books")
    public List<BookSearchResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType, @RequestParam("value")String value){
        return bookServiceInterf.findFilteredBooks(bookFilterType,value);
    }
    @GetMapping("/get/book")
    public ResponseEntity getBook(@RequestParam("bookId")int bookId){
        try{
            Book book=bookServiceInterf.findById(bookId);
            return new ResponseEntity(book,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity("Book with bookId: "+bookId+" not exists",HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete/book")
    public ResponseEntity deleteBook (@RequestParam("bookId") int bookId){
        try{
            Book book = bookServiceInterf.findById(bookId);
            if(book.getStudent()!=null){
                throw new BookException("Book has not been returned by the student with studentId: "+book.getStudent().getId());
            }
            bookServiceInterf.delete(book);
            return new ResponseEntity("Book Deleted Successfully",HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity("Book with bookId: "+bookId+" not exists",HttpStatus.BAD_REQUEST);
        }




    }
    @PutMapping("/update/book")
    public ResponseEntity updateBook(@RequestParam("bookId")int bookId,@Valid @RequestBody BookCreateRequest bookCreateRequest){

        try {
            Book book = bookServiceInterf.findById(bookId);
            Book updatedBook = bookServiceInterf.updateBook(bookId, bookCreateRequest);
            return new ResponseEntity("Book with bookId: " + bookId + " is updated", HttpStatus.ACCEPTED);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity("Book with bookId: "+bookId+" not exists",HttpStatus.BAD_REQUEST);
        }


    }









}

