package com.galmv_.niceia.domain.post;

import com.galmv_.niceia.domain.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
