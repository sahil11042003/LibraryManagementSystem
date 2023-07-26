package com.example.LibraryManagementSystem.serviceImpl;


import com.example.LibraryManagementSystem.model.MyUser;
import com.example.LibraryManagementSystem.repository.MyUserCacheRepository;
import com.example.LibraryManagementSystem.repository.MyUserRepositoryInterf;
import com.example.LibraryManagementSystem.requestDto.MyUserCreateRequest;
import com.example.LibraryManagementSystem.service.MyUserServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements MyUserServiceInterf, UserDetailsService {

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Autowired
    MyUserRepositoryInterf myUserRepositoryInterf;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public MyUser create(MyUserCreateRequest user) throws DataIntegrityViolationException {


        String encryptedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return myUserRepositoryInterf.save(user.toMyUser());
    }


    //    @Override
//    public MyUser loadUserByUsername(String email) throws UsernameNotFoundException {
//        return myUserRepositoryInterf.findByEmail(email);
//    }
    @Override
    public MyUser loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser user= myUserCacheRepository.get(email);
        if(user==null){
            user = myUserRepositoryInterf.findByEmail(email);
            if(user!=null){
                myUserCacheRepository.set(user);
            }
        }
        return user;

    }
}
