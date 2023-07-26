package com.example.LibraryManagementSystem.repositoryImpl;

import com.example.LibraryManagementSystem.model.MyUser;
import com.example.LibraryManagementSystem.repository.MyUserCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class MyUserCacheRepositoryImpl implements MyUserCacheRepository {


    private String USER_KEY_PREFIX = "My_User::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(MyUser myUser) {
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser,12, TimeUnit.HOURS);
    }

    @Override
    public MyUser get(String username) {
        return (MyUser) redisTemplate.opsForValue().get(getKey(username));
    }

    private String getKey(String username) {
        return USER_KEY_PREFIX+username;
    }

}
