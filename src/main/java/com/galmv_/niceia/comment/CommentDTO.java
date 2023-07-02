package com.galmv_.niceia.comment;

import com.galmv_.niceia.post.Post;
import com.galmv_.niceia.student.Student;

public record CommentDTO(String text, Post post, Student student) {
}
