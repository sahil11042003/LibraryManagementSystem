package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.responseDto.TransactionSearchResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private String externalId;

    private double payment;
    @ManyToOne
    @JsonIgnoreProperties({"transactionList", "bookList"})
    private Book book;

    @ManyToOne
    @JsonIgnoreProperties({"transactionList", "bookList"})
    private Student student;

    @CreationTimestamp
    private Date createdOn;

    public TransactionSearchResponse toTransactionSearchResponse() {
        return TransactionSearchResponse.builder().id(id).transactionType(transactionType).
                externalId(externalId).payment(payment).book(book).student(student).createdOn(createdOn).build();

    }
}
