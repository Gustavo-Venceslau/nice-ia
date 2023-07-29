package com.galmv_.niceia.testFactories.data;

import com.galmv_.niceia.domain.post.Post;
import com.galmv_.niceia.domain.student.Student;

public class MakePost {

    public static Post execute(Student student){

        return Post
                .builder()
                .title("Good Morning")
                .imageURL("ImageURL")
                .student(student)
                .build();
    }
}
