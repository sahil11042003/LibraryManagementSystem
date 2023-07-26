package com.example.LibraryManagementSystem.controller;


import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.requestDto.AuthorCreateRequest;
import com.example.LibraryManagementSystem.service.AuthorServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorController {
    @Autowired
    AuthorServiceInterf authorServiceInterf;

    @PostMapping("/create/author")
    public ResponseEntity createAuthor(@Valid @RequestBody AuthorCreateRequest authorCreateRequest){

        try{
            authorServiceInterf.createAuthor(authorCreateRequest);
            return new ResponseEntity("Author Created Successfully.", HttpStatus.ACCEPTED);
        }
        catch(DataIntegrityViolationException e){
            return new ResponseEntity("Author with Email: "+authorCreateRequest.getEmail()+" already exists. Try using another email.",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/author")
    public ResponseEntity getAuthor(@RequestParam("authorEmail") String email){
        Author author= authorServiceInterf.findByEmail(email);
        if(author==null){
            return new ResponseEntity("Author with Email: "+email+" doesn't exists.",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(author,HttpStatus.OK);


    }

    @DeleteMapping("/delete/author")
    public ResponseEntity deleteAuthor(@RequestParam("authorEmail") String email){

        Author author= authorServiceInterf.findByEmail(email);
        if(author==null){
            return new ResponseEntity("Author with Email: "+email+" doesn't exists.",HttpStatus.ACCEPTED);
        }
        authorServiceInterf.delete(author);
        return new ResponseEntity("Author with Email: "+email+" Deleted Successfully.",HttpStatus.ACCEPTED);



    }

    @PutMapping("/update/author")
    public ResponseEntity updateAuthor(@RequestParam("authorEmail")String email,@Valid@RequestBody AuthorCreateRequest authorCreateRequest){
        try{
            Author author= authorServiceInterf.findByEmail(email);
            if(author==null){
                return new ResponseEntity("Author with Email: "+email+" doesn't exists. Try using Another email",HttpStatus.BAD_REQUEST);
            }
            authorServiceInterf.updateAuthor(email,authorCreateRequest);
            return  new ResponseEntity("Author with Email: "+authorCreateRequest.getEmail()+" Updated Successfully",HttpStatus.ACCEPTED);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity("Duplicate value on unique fields",HttpStatus.BAD_REQUEST);
        }


    }



}

