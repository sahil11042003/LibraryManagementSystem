package com.example.LibraryManagementSystem.requestDto;

import com.example.LibraryManagementSystem.model.MyUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyUserCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;


    private String password;


    private String authority;

    public MyUser toMyUser(){
        return MyUser.builder().name(name).email(email).password(password).authority(authority).build();
    }
}
