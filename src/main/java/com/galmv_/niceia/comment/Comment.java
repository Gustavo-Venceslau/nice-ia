package com.galmv_.niceia.comment;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "comment_post")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "comment_student")
    private Student student;

}
