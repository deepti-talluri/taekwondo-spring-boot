package com.example.database.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "TRANSACTION")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(generator = "TRANSACTION_SEQUENCE")
    @SequenceGenerator(sequenceName = "TRANSACTION_SEQUENCE", name = "TRANSACTION_SEQUENCE")
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Basic
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Basic
    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Basic
    @Column(name = "TRANSACTION_DATE", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;

}
