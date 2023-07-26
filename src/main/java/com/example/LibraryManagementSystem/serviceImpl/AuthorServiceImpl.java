package com.example.LibraryManagementSystem.serviceImpl;

import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepositoryInterf;
import com.example.LibraryManagementSystem.repository.BookRepositoryInterf;
import com.example.LibraryManagementSystem.requestDto.AuthorCreateRequest;
import com.example.LibraryManagementSystem.service.AuthorServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorServiceInterf {
    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;
    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Override
    public Author findByEmail(String email) {
        return authorRepositoryInterf.findByEmail(email);
    }

    @Override
    public Author createAuthor(AuthorCreateRequest authorCreateRequest) {
        return authorRepositoryInterf.save(authorCreateRequest.toAuthor());
//         authorRepositoryInterf.save(author);
//         return author;
    }

    @Override
    public void delete(Author author)  {
        List<Book> books = bookRepositoryInterf.findByAuthor(author);
        for(Book book:books){
            book.setAuthor(null);
            bookRepositoryInterf.save(book);
        }
        authorRepositoryInterf.delete(author);


    }

    @Override
    public Author updateAuthor(String email, AuthorCreateRequest authorCreateRequest)throws DataIntegrityViolationException {
        Author author = authorRepositoryInterf.findByEmail(email);
        author.setName(authorCreateRequest.getName());
        author.setCountry(authorCreateRequest.getCountry());
        author.setAge(authorCreateRequest.getAge());
        author.setEmail(authorCreateRequest.getEmail());
        return authorRepositoryInterf.save(author);
    }
}
