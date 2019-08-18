package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Student;
import com.example.database.persistence.entity.Transaction;
import com.example.database.persistence.repo.StudentRepository;
import com.example.database.persistence.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/{studentId}")
    public Transaction addTransaction(@RequestBody Transaction transaction, @PathVariable("studentId") long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            throw new NotFoundException();
        }
        transaction.setStudent(optionalStudent.get());
        return transactionRepository.save(transaction);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{startDate}/{endDate}")
    public List<Transaction> getTransactionsBetweenDates(@PathVariable("startDate")
                                                         @DateTimeFormat(pattern = "MM-dd-yyyy") Date startDate,
                                                         @PathVariable("endDate")
                                                         @DateTimeFormat(pattern = "MM-dd-yyyy") Date endDate) {
        return transactionRepository.findAllByDateBetween(startDate, endDate);
    }

}
