package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Rank;
import com.example.database.persistence.entity.Student;
import com.example.database.persistence.repo.RankRepository;
import com.example.database.persistence.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rank")
public class RankController {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Rank addRank(@RequestBody Rank rank) {
        return rankRepository.save(rank);
    }

    @GetMapping
    public List<Rank> getRanks() {
        return rankRepository.findAll();
    }

    @PostMapping("/add-rank/{rankId}/{studentId}")
    public Student addRank(@PathVariable("rankId") long rankId,
                           @PathVariable("studentId") long studentId) {
        Optional<Rank> optionalRank = rankRepository.findById(rankId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (!optionalRank.isPresent() || !optionalStudent.isPresent()) {
            throw new NotFoundException();
        }
        Rank rank = optionalRank.get();
        Student student = optionalStudent.get();
        student.getRanks().add(rank);
        studentRepository.save(student);
        return student;
    }

}
