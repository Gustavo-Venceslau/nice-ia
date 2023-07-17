package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment{

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
