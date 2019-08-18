package com.example.database.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CLASS_MEETING")
@Getter
@Setter
public class ClassMeeting {
    @Id
    @GeneratedValue(generator = "CLASS_MEETING_SEQUENCE")
    @SequenceGenerator(sequenceName = "CLASS_MEETING_SEQUENCE", name = "CLASS_MEETING_SEQUENCE")
    @Column(name = "CLASS_MEETING_ID")
    private Long id;

    @Basic
    @Column(name = "MEETING_DATE", nullable = false)
    private Date meetingDate;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID", nullable = false)
    @JsonIgnore
    private Class offeredClass;

    //each meeting has multiple students
    @ManyToMany(mappedBy = "classMeetings")
    private Set<Student> students = new HashSet<>();

}
