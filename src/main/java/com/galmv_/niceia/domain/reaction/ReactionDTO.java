package com.galmv_.niceia.domain.reaction;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.student.Student;

import java.util.UUID;

public record ReactionDTO(Type type, UUID post, UUID comment, UUID student){
}
