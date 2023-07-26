package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.repositoryImpl.MyUserCacheRepositoryImpl;
import com.example.LibraryManagementSystem.requestDto.MyUserCreateRequest;
import com.example.LibraryManagementSystem.service.MyUserServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    MyUserCacheRepositoryImpl myUser;
    @Autowired
    MyUserServiceInterf myUserServiceInterf;
    @PostMapping("/signUp")
    public ResponseEntity createUser(@Valid @RequestBody MyUserCreateRequest user) {
        try {
            myUser.set(myUserServiceInterf.create(user));
            return new ResponseEntity("User Saved Successfully", HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity("User with with email: "+user.getEmail()+" already exists. Try using another email.", HttpStatus.BAD_REQUEST);

        }
    }

}
