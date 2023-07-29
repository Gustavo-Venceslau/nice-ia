package com.galmv_.niceia.testFactories.data;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.reaction.Enums.Type;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.student.Student;

public class MakeReaction {

    public static Reaction execute(Post post, Comment comment, Student student){

        return Reaction
                .builder()
                .type(Type.LIKE)
                .post(post)
                .comment(comment)
                .student(student)
                .build();
    }
}
