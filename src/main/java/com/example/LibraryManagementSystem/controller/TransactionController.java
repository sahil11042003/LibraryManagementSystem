package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.responseDto.TransactionSearchResponse;
import com.example.LibraryManagementSystem.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TransactionController {
    @Autowired
    TransactionServiceInterf transactionServiceInterf;

    @PostMapping("/createTransaction/{transactionType}")
    public ResponseEntity transact(

            @RequestParam("studentId") int studentId,
            @RequestParam("bookId")int bookId,
            @PathVariable("transactionType")String transactionType){
        try{
            return new ResponseEntity("Transaction Completed Successfully with UUID: "+transactionServiceInterf.transact(studentId,bookId,transactionType), HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity("Incorrect studentEmail/bookId and try again",HttpStatus.BAD_REQUEST);
        }



    }
    @GetMapping("/getTransaction/{transactionType}")
    public List<TransactionSearchResponse> getTransact(@PathVariable TransactionType transactionType){
        return transactionServiceInterf.getAllTransactions(transactionType);

    }

}
