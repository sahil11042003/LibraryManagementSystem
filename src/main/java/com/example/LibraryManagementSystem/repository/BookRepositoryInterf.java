package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryInterf extends JpaRepository<Book,Integer> {


    List<Book> findByName(String name);
    List<Book> findByAuthor_name(String authorName);
    List<Book> findByGenre(Genre genre);
    List<Book> findByCost(int cost);

    List<Book>findByAuthor(Author author);






}
