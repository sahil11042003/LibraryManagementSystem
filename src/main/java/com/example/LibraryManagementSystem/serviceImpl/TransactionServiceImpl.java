package com.example.LibraryManagementSystem.serviceImpl;


import com.example.LibraryManagementSystem.enums.TransactionType;
import com.example.LibraryManagementSystem.exception.TransactionServiceException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.TransactionRepositoryInterf;
import com.example.LibraryManagementSystem.responseDto.TransactionSearchResponse;
import com.example.LibraryManagementSystem.service.BookServiceInterf;
import com.example.LibraryManagementSystem.service.StudentServiceInterf;
import com.example.LibraryManagementSystem.service.TransactionServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {

    @Autowired
    StudentServiceInterf studentServiceInterf;

    @Autowired
    BookServiceInterf bookServiceInterf;

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Value("${book.maxAllowedDays}")
    int maxAllowedDays;

    @Override
    public String transact(int studentId, int bookId, String transactionType) {

        Optional<Student> student= studentServiceInterf.findById(studentId);
        if(!student.isPresent()){
            throw new TransactionServiceException("Student not present in the library");
        }

        Book book = bookServiceInterf.findById(bookId);
        if(book==null){
            throw new TransactionServiceException("Book not present in the library");
        }
        if(TransactionType.valueOf(transactionType).equals(TransactionType.ISSUE)){
            if(book.getStudent()!=null){
                throw new TransactionServiceException("Book not Available for Issue");
            }
            Transaction transaction=Transaction.builder().
                    externalId(UUID.randomUUID().toString()).
                    transactionType(TransactionType.ISSUE).
                    book(book).student(student.get()).build();

            transactionRepositoryInterf.save(transaction);

            book.setStudent(student.get());

            bookServiceInterf.save(book);

            return transaction.getExternalId();
        }
        else if(TransactionType.valueOf(transactionType).equals(TransactionType.RETURN)){
            if(book.getStudent()==null){
                throw new TransactionServiceException("Book not Issued to Any  Student");
            }
            if(book.getStudent().getId()!=studentId){
                throw new TransactionServiceException("Book not Issued to this  Student");
            }
            //logic query for finding the transaction of the book and student below is the implementation code for the same.
            //select top 1 from transaction where  book_id=bookId and student_id= studentId and transaction_type=0 order  by transaction_date dsc limit by 1
            Transaction issueTransaction =
                    transactionRepositoryInterf
                            .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc
                                    (book,student.get(),TransactionType.ISSUE);

            Transaction transaction=Transaction.builder().
                    externalId(UUID.randomUUID().toString()).
                    transactionType(TransactionType.RETURN).payment(calculateFine(issueTransaction)).
                    book(book).student(student.get()).build();

            transactionRepositoryInterf.save(transaction);

            book.setStudent(null);

            bookServiceInterf.save(book);

            return transaction.getExternalId();
        }
        return null;

    }

    @Override
    public List<TransactionSearchResponse> getAllTransactions(TransactionType transactionType) {
        return AllTransactions(transactionType).stream().
                map(transaction -> transaction.toTransactionSearchResponse()).collect(Collectors.toList());
    }


    public List<Transaction> AllTransactions(TransactionType transactionType) {
        return transactionRepositoryInterf.findByTransactionType(transactionType);
    }


    private double calculateFine(Transaction issueTxn){
        long issueTime =issueTxn.getCreatedOn().getTime();
        long returnTime =System.currentTimeMillis();
        long diff= returnTime-issueTime;
        long daysPassed= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(daysPassed>maxAllowedDays){
            return ((daysPassed-maxAllowedDays)*10);
        }
        return 0.0;
    }
}
