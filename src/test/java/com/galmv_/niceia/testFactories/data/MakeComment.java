package com.galmv_.niceia.testFactories.data;

import com.galmv_.niceia.domain.comment.Comment;
import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;

public class MakeComment {

    public static Comment execute(Post post, Student student){

        return Comment
                .builder()
                .text("Good way, guys!")
                .post(post)
                .student(student)
                .build();
    }
}
