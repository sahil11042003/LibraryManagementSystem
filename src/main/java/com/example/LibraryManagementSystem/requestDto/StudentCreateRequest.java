package com.example.LibraryManagementSystem.requestDto;

import com.example.LibraryManagementSystem.enums.AccountStatus;
import com.example.LibraryManagementSystem.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {
    private String name;
    private String email;
    private String contact;
    private String address;

    public Student toStudent() {
        return Student.builder().name(name).email(email).contact(contact).address(address).accountStatus(AccountStatus.ACTIVE).build();
    }
}