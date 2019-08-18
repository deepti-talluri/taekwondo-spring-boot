package com.example.database.persistence.repo;

import com.example.database.persistence.entity.ClassMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassMeetingRepository extends JpaRepository<ClassMeeting, Long> {
}
