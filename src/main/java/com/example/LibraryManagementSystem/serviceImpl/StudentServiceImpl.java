package com.example.LibraryManagementSystem.serviceImpl;

import com.example.LibraryManagementSystem.exception.StudentException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.repository.StudentRepositoryInterf;
import com.example.LibraryManagementSystem.requestDto.StudentCreateRequest;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentServiceInterf {
    @Autowired
    StudentRepositoryInterf studentRepositoryInterf;
    @Override
    public Student save(StudentCreateRequest studentCreateRequest) throws DataIntegrityViolationException {
        return studentRepositoryInterf.save(studentCreateRequest.toStudent());
    }

    @Override
    public Optional<Student> findById(int studentId) {
        return studentRepositoryInterf.findById(studentId);
    }

    @Override
    public void delete(String email) {
        studentRepositoryInterf.deleteByEmail(email);
    }

    @Override
    public Student updateBook(String email, StudentCreateRequest studentCreateRequest) {
        Student student =studentRepositoryInterf.findByEmail(email);
        student.setName(studentCreateRequest.getName());
        student.setEmail(studentCreateRequest.getEmail());
        student.setContact(studentCreateRequest.getContact());
        student.setAddress(studentCreateRequest.getAddress());
        return studentRepositoryInterf.save(student);



    }

    @Override
    public Student findByEmail(String email) {
        return studentRepositoryInterf.findByEmail(email);
    }

    @Override
    public List<Book> getAllBooks(int studentId) {
        Optional<Student>student=studentRepositoryInterf.findById(studentId);
        if(student.isPresent()){
            return student.get().getBookList();
        }
        else throw new StudentException("Student with not found with ID:"+studentId);


    }

}
