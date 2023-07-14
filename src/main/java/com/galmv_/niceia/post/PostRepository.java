package com.galmv_.niceia.post;

import com.galmv_.niceia.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    void deleteAllByStudent(Student student);
}
