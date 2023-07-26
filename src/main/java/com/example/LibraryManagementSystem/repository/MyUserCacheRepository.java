package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.MyUser;

public interface MyUserCacheRepository {
    public void set(MyUser myUser);
    public MyUser get(String username);
}
