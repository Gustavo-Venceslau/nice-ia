package com.galmv_.niceia.domain.reaction;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, UUID> {

    List<Reaction> findAllByStudent(Student student);
    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
    void deleteAllByStudent(Student student);
    void deleteAllByPost(Post post);
}
