package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Class;
import com.example.database.persistence.repo.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @PostMapping
    public Class addClass(@RequestBody Class aClass) {
        return classRepository.save(aClass);
    }

    @GetMapping
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    @DeleteMapping("/{classId}")
    public Class removeClass(@PathVariable("classId") long classId) {
        Optional<Class> optionalClass = classRepository.findById(classId);
        if (!optionalClass.isPresent()) {
            throw new NotFoundException();
        }
        classRepository.deleteById(classId);
        return optionalClass.get();
    }

}
