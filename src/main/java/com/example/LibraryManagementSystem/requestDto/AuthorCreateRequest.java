package com.example.LibraryManagementSystem.requestDto;


import com.example.LibraryManagementSystem.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateRequest {
    private String name;
    private String country;
    private int age;

    private String email;
    public Author toAuthor(){
        return Author.builder().name(name).country(country).age(age).email(email).build();
    }


}
