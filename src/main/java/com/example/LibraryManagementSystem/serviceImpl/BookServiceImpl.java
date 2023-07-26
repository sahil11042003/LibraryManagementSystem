package com.example.LibraryManagementSystem.serviceImpl;

import com.example.LibraryManagementSystem.enums.BookFilterType;
import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepositoryInterf;
import com.example.LibraryManagementSystem.repository.TransactionRepositoryInterf;
import com.example.LibraryManagementSystem.requestDto.AuthorCreateRequest;
import com.example.LibraryManagementSystem.requestDto.BookCreateRequest;
import com.example.LibraryManagementSystem.responseDto.BookSearchResponse;
import com.example.LibraryManagementSystem.service.AuthorServiceInterf;
import com.example.LibraryManagementSystem.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookServiceInterf {
    @Autowired
    BookRepositoryInterf bookRepositoryInterf;
    @Autowired
    AuthorServiceInterf authorServiceInterf;
    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Override
    public Book create(BookCreateRequest bookCreateRequest) {
        Book book =bookCreateRequest.toBook();



        if (bookCreateRequest.getAuthorCreateRequest() != null) {
            AuthorCreateRequest authorCreateRequest = bookCreateRequest.getAuthorCreateRequest();
            Author author = authorServiceInterf.findByEmail(authorCreateRequest.toAuthor().getEmail());

            if (author == null) {
                author = authorServiceInterf.createAuthor(authorCreateRequest);
            }

            book.setAuthor(author);
        }

        return bookRepositoryInterf.save(book);
    }
    public List<BookSearchResponse> findFilteredBooks(BookFilterType bookFilterType, String value){
        return find(bookFilterType, value).stream()
                .map(book -> book.toBookSearchResponse())
                .collect(Collectors.toList());
    }

    //    @Override
    public List<Book> find(BookFilterType bookFilterType, String value) {
        switch(bookFilterType){
            case NAME:
                return bookRepositoryInterf.findByName(value);
            case AUTHOR_NAME:
                return bookRepositoryInterf.findByAuthor_name(value);
            case GENRE:
                Genre genre= Genre.valueOf(value.toUpperCase());
                return bookRepositoryInterf.findByGenre(genre);
            case ID:
                return bookRepositoryInterf.findAllById(new ArrayList<Integer>(Integer.parseInt(value)));
            default:
                return bookRepositoryInterf.findAll();
        }

    }


    @Override
    public Book findById(int bookId) {
        return bookRepositoryInterf.findById(bookId).get();
    }

    @Override
    public Book save(Book book) {
        return bookRepositoryInterf.save(book);
    }

    @Override
    public void delete(Book book) {
        List<Transaction> transactions= transactionRepositoryInterf.findByBook(book);
        for(Transaction transaction : transactions){
            transactionRepositoryInterf.delete(transaction);
        }

        bookRepositoryInterf.delete(book);
    }

    @Override
    public Book updateBook(int bookId,BookCreateRequest bookCreateRequest)throws DataIntegrityViolationException {
        Book book =bookRepositoryInterf.findById(bookId).get();
        book.setName(bookCreateRequest.getName());
        book.setCost(bookCreateRequest.getCost());
        book.setGenre(bookCreateRequest.getGenre());
        if (bookCreateRequest.getAuthorCreateRequest() != null) {
            AuthorCreateRequest authorCreateRequest = bookCreateRequest.getAuthorCreateRequest();
            Author author = authorServiceInterf.findByEmail(authorCreateRequest.getEmail());

            if (author == null) {
                author = authorServiceInterf.createAuthor(authorCreateRequest);
            }
            else{
                author.setName(bookCreateRequest.getAuthorCreateRequest().getName());
                author.setCountry(bookCreateRequest.getAuthorCreateRequest().getCountry());
                author.setAge(bookCreateRequest.getAuthorCreateRequest().getAge());
            }

            book.setAuthor(author);
        }

        return bookRepositoryInterf.save(book);





    }


}

