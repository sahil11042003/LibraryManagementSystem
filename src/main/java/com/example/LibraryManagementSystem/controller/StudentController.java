package com.example.LibraryManagementSystem.controller;


import com.example.LibraryManagementSystem.exception.StudentException;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.requestDto.StudentCreateRequest;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
public class StudentController {
    @Autowired
    StudentServiceInterf studentServiceInterf;
    @PostMapping("/create/student")
    public ResponseEntity saveStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){

        try {
            studentServiceInterf.save(studentCreateRequest);
            return new ResponseEntity("Student Saved Successfully", HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity("Student with email: "+studentCreateRequest.getEmail()+" already exists. Try using another email.",HttpStatus.BAD_REQUEST);

        }



    }
    @GetMapping("/get/student")
    public ResponseEntity getStudent(@RequestParam("studentEmail")String email){

        try{
            Student student= studentServiceInterf.findByEmail(email);
            return new ResponseEntity(student,HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity("Student with email: "+email+" doesn't exists",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/student")
    public ResponseEntity deleteBook(@RequestParam("studentEmail")String email)  {
        try{
            Student student= studentServiceInterf.findByEmail(email);
            if(!student.getBookList().isEmpty()){
                throw new StudentException("Student has not returned the books");
            }
            studentServiceInterf.delete(email);
            return new ResponseEntity("Student Deleted Successfully",HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity("Student with email: "+email+" doesn't exists",HttpStatus.BAD_REQUEST);

        }
        catch(NullPointerException e){
            return new ResponseEntity("Student with email: "+email+" doesn't exists",HttpStatus.BAD_REQUEST);

        }


    }

    @PutMapping("/update/student")
    public ResponseEntity updateStudent(@RequestParam("studentEmail")String email, @Valid@RequestBody StudentCreateRequest studentCreateRequest){

        try{
            Student student= studentServiceInterf.findByEmail(email);
            Student updatedStudent= studentServiceInterf.updateBook(email,studentCreateRequest);
            return new ResponseEntity("Student with email: "+email+" is updated", HttpStatus.ACCEPTED);

        }
        catch(NullPointerException e){
            return new ResponseEntity("Student with email: "+email+" doesn't exists",HttpStatus.BAD_REQUEST);

        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity("Duplicate value on unique fields",HttpStatus.BAD_REQUEST);
        }

    }


}

