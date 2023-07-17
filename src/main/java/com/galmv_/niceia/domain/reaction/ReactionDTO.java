package com.galmv_.niceia.domain.reaction;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.student.Student;

public record ReactionDTO(Type type, Post post, Comment comment, Student student){
}
