package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryInterf extends JpaRepository<Transaction,Integer> {
//    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,TransactionType transactionType);

    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,
                                                                       TransactionType transactionType);


    List<Transaction> findByBook(Book book);
    List<Transaction>findByTransactionType(TransactionType transactionType);

}
