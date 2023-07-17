package com.galmv_.niceia.domain.post;

import com.galmv_.niceia.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    void deleteAllByStudent(Student student);

    List<Post> findAllByStudent(Student student);
}
