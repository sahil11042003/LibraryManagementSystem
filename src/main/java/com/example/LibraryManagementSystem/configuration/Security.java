package com.example.LibraryManagementSystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeHttpRequests().
                antMatchers("/signUp").permitAll().

                antMatchers("/get/**").hasAnyAuthority("STUDENT","ADMIN")
                .antMatchers("/delete/**","/update/**","/create/**").hasAuthority("ADMIN")
                .antMatchers("/createTransaction/**").hasAnyAuthority("STUDENT","ADMIN")
                .antMatchers("/getTransaction/**").hasAuthority("ADMIN")
                .antMatchers("/search/books").hasAnyAuthority("STUDENT","ADMIN")

                .and().formLogin().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
