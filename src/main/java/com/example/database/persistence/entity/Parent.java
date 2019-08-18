package com.example.database.persistence.entity;

import com.example.database.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "PARENT")
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(generator = "PARENT_SEQUENCE")
    @SequenceGenerator(sequenceName = "PARENT_SEQUENCE", name = "PARENT_SEQUENCE")
    @Column(name = "PARENT_ID")
    private Long id;

    @Basic
    @Column(name = "FATHER_NAME")
    private String father;

    @Basic
    @Column(name = "MOTHER_NAME")
    private String mother;

    @Basic
    @Column(name = "FATHER_MOBILE")
    private String fatherMobileNumber;

    @Basic
    @Column(name = "MOTHER_MOBILE")
    private String motherMobileNumber;

    @Basic
    @Column(name = "FATHER_EMAIL")
    private String fatherEmail;

    @Basic
    @Column(name = "MOTHER_EMAIL")
    private String motherEmail;

    @OneToOne(mappedBy = "parent")
    @JsonIgnore
    private Student student;

    public boolean isEmpty() {
        return Utils.trimToNull(father) == null &&
                Utils.trimToNull(mother) == null &&
                Utils.trimToNull(fatherMobileNumber) == null &&
                Utils.trimToNull(motherMobileNumber) == null &&
                Utils.trimToNull(fatherEmail) == null &&
                Utils.trimToNull(motherEmail) == null;
    }
}
