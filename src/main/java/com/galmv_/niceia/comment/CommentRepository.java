package com.galmv_.niceia.comment;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByStudent(Student student);
    void deleteAllByStudent(Student student);

    void deleteAllByPost(Post post);
}
