package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.responseDto.BookSearchResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"transactionList"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList"})
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList","transactionList"})
    private Student student;


    @JsonIgnoreProperties({"bookList","book","student","transactionList"})
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    public BookSearchResponse toBookSearchResponse(){
        return BookSearchResponse.builder().id(id).name(name).
                author(author).genre(genre).student(student).createdOn(createdOn).updatedOn(updatedOn).build();
    }



}
