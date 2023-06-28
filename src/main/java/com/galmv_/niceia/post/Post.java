package com.galmv_.niceia.post;

import com.galmv_.niceia.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Post {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String imageURL;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
