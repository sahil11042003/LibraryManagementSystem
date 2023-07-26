package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.responseDto.TransactionSearchResponse;

import java.util.List;

public interface TransactionServiceInterf {

    String transact(int studentId, int bookId, String transactionType);

    List<TransactionSearchResponse> getAllTransactions(TransactionType transactionType);


}
