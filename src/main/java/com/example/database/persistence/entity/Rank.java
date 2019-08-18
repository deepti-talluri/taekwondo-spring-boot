package com.example.database.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "RANK")
@Getter
@Setter
public class Rank {
    @Id
    @GeneratedValue(generator = "RANK_SEQUENCE")
    @SequenceGenerator(sequenceName = "RANK_SEQUENCE", name = "RANK_SEQUENCE")
    @Column(name = "RANK_ID")
    private Long id;

    @Basic
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "ranks")
    private Set<Student> students = new HashSet<>();

}
