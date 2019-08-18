package com.example.database.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CLASS")
@Getter
@Setter
public class Class {
    @Id
    @GeneratedValue(generator = "CLASS_SEQUENCE")
    @SequenceGenerator(sequenceName = "CLASS_SEQUENCE", name = "CLASS_SEQUENCE")
    @Column(name = "CLASS_ID")
    private Long id;

    @Basic
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    //Each class has multiple meetings
    @OneToMany(mappedBy = "offeredClass")
    private Set<ClassMeeting> classMeetings = new HashSet<>();

    public void addMeeting(ClassMeeting classMeeting){
        classMeeting.setOfferedClass(this);
        classMeetings.add(classMeeting);
    }

}
