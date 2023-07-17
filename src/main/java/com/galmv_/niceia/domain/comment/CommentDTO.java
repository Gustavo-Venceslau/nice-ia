package com.galmv_.niceia.domain.comment;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;

public record CommentDTO(String text, Post post, Student student) {
}
