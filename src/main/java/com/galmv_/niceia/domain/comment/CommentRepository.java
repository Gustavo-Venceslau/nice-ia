package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByStudent(Student student);
    List<Comment> findAllByPost(Post post);
    void deleteAllByStudent(Student student);
    void deleteAllByPost(Post post);
}
