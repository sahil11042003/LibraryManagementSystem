package com.example.LibraryManagementSystem.requestDto;

import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  BookCreateRequest {
    @NotBlank
    private String name;
    @Positive
    private int cost;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    @NotNull
    private AuthorCreateRequest authorCreateRequest;

    public Book toBook() {
        return Book.builder().name(name).cost(cost).genre(genre).author(authorCreateRequest.toAuthor()).build();
    }
}
