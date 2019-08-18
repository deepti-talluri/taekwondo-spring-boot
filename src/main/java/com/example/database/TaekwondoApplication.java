package com.example.database;

import com.example.database.persistence.entity.Class;
import com.example.database.persistence.entity.*;
import com.example.database.persistence.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TaekwondoApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassMeetingRepository meetingRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaekwondoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student();
        student.setActive(true);
        student.setAddress("384 Sip Avenue");
        student.setParent(null);
        student.setEmail("abc@gmail.com");
        student.setDateOfBirth(new Date());
        student.setDateOfJoining(new Date());
        student.setMobileNumber("12345");
        student.setName("Shamshuddin");

        Parent parent = new Parent();
        parent.setFather("Noor");
        parent.setMother("Khadeerunnisa");
        student.setParent(parent);

        Rank rank = new Rank();
        rank.setDescription("Rank 1");
        rank = rankRepository.save(rank);
        student.getRanks().add(rank);
        student = studentRepository.save(student);


        Class myClass = new Class();
        myClass.setDescription("Class 1");
        ClassMeeting classMeeting = new ClassMeeting();
        classMeeting.setMeetingDate(new Date());
        myClass.addMeeting(classMeeting);

        classRepository.save(myClass);
        classMeeting = meetingRepository.save(classMeeting);

        student.getClassMeetings().add(classMeeting);
        student = studentRepository.save(student);

        Transaction transaction = new Transaction();
        transaction.setAmount(120d);
        transaction.setDescription("Membership");
        transaction.setDate(new Date());
        transaction.setStudent(student);

        transactionRepository.save(transaction);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(100d);
        transaction2.setDescription("Blah blah");
        transaction2.setDate(new Date());
        transaction2.setStudent(student);

        transactionRepository.save(transaction2);

    }
}
