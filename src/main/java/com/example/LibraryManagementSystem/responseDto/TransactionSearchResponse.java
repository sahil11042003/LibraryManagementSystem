package com.example.LibraryManagementSystem.responseDto;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"student"})

public class TransactionSearchResponse {

    private int id;
    private TransactionType transactionType;

    private String externalId;

    private double payment;
    @ManyToOne
    @JsonIgnoreProperties({"transactionList","bookList"})
    private Book book;

    @ManyToOne
    @JsonIgnoreProperties({"transactionList","bookList"})
    private Student student;

    @CreationTimestamp
    private Date createdOn;
}
