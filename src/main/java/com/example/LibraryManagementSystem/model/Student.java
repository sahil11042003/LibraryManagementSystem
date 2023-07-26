package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.enums.AccountStatus;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"transactionList"})


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String contact;
    private String address;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @JsonIgnoreProperties({"student"})
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;


    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedON;

}
