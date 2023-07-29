package com.galmv_.niceia.testFactories.data;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;

public class MakeStudent {

    public static Student execute(){

        return Student
                .builder()
                .firstName("Gustavo")
                .lastName("de Almeida")
                .email("gustavodealmeida@gmail.com")
                .password("1234")
                .studentRole(StudentRole.USER)
                .build();
    }
}
