package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Parent;
import com.example.database.persistence.entity.Student;
import com.example.database.persistence.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(produces = "application/json")
    public Student addStudent(@RequestBody Student student) {
        //If the parent is empty
        Parent parent = student.getParent();
        if (parent != null && parent.isEmpty()) {
            student.setParent(null);
        }
        //activate the student
        student.setActive(true);
        //add this student to the repository
        return studentRepository.save(student);
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public Student removeStudent(@PathVariable("id") long id) {
        //find the student with given id
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent())
            throw new NotFoundException();
        Student student = optionalStudent.get();
        student.setActive(false);
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAllByActiveTrue();
    }
}
