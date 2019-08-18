package com.example.database.controller;

import com.example.database.exception.NotFoundException;
import com.example.database.persistence.entity.Class;
import com.example.database.persistence.entity.ClassMeeting;
import com.example.database.persistence.entity.Student;
import com.example.database.persistence.repo.ClassMeetingRepository;
import com.example.database.persistence.repo.ClassRepository;
import com.example.database.persistence.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/class-meeting")
public class ClassMeetingController {

    @Autowired
    private ClassMeetingRepository classMeetingRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/{classId}")
    public ClassMeeting addMeeting(@PathVariable("classId") long classId,
                                   @RequestBody ClassMeeting classMeeting) {
        Optional<Class> optionalClass = classRepository.findById(classId);
        if (!optionalClass.isPresent()) {
            throw new NotFoundException();
        }
        Class theClass = optionalClass.get();
        theClass.addMeeting(classMeeting);
        return classMeetingRepository.save(classMeeting);
    }

    @PostMapping("/mark-attendance/{meetingId}/{studentId}")
    public ClassMeeting addAttendance(@PathVariable("meetingId") long meetingId,
                                      @PathVariable("studentId") long studentId) {
        Optional<ClassMeeting> optionalClassMeeting = classMeetingRepository.findById(meetingId);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (!optionalClassMeeting.isPresent() || !optionalStudent.isPresent()) {
            throw new NotFoundException();
        }
        ClassMeeting classMeeting = optionalClassMeeting.get();
        Student student = optionalStudent.get();
        student.getClassMeetings().add(classMeeting);
        studentRepository.save(student);
        return classMeeting;
    }


}
