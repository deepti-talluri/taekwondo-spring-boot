package com.example.database.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "STUDENT")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(generator = "STUDENT_SEQUENCE")
    @SequenceGenerator(sequenceName = "STUDENT_SEQUENCE", name = "STUDENT_SEQUENCE")
    private Long id;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @Basic
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE", nullable = false)
    private Date dateOfBirth;

    @Basic
    @Column(name = "DATE_OF_JOINING", columnDefinition = "DATE", nullable = false)
    private Date dateOfJoining;

    @Basic
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "ACTIVE")
    @JsonIgnore
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "PARENT_ID")
    private Parent parent;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "STUDENT_RANK",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "RANK_ID"))
    @JsonIgnore
    private Set<Rank> ranks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "STUDENT_MEETING",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEETING_ID"))
    @JsonIgnore
    private Set<ClassMeeting> classMeetings = new HashSet<>();

}
