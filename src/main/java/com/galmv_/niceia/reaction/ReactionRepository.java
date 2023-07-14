package com.galmv_.niceia.reaction;

import com.galmv_.niceia.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, UUID> {

    List<Reaction> findAllByStudent(Student student);
    void deleteAllByStudent(Student student);
}
