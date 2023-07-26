package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.model.MyUser;
import com.example.LibraryManagementSystem.requestDto.MyUserCreateRequest;

public interface MyUserServiceInterf {
    MyUser create(MyUserCreateRequest user);
}
