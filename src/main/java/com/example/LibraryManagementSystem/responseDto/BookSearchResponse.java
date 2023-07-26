package com.example.LibraryManagementSystem.responseDto;

import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"transactionList"})
@Data
@AllArgsConstructor
@Builder
public class BookSearchResponse {
    private int id;
    private String name;
    private int cost;
    private Genre genre;

    @JsonIgnoreProperties({"bookList"})
    private Author author;

    @JsonIgnoreProperties({"bookList"})
    private Student student;


    private List<Transaction> transactionList;

    private Date createdOn;
    private Date updatedOn;

}
