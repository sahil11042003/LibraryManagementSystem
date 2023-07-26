package com.example.LibraryManagementSystem.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Password {
    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
