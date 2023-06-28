package com.galmv_.niceia.studentClass;

import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.enums.StudentRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStudentClass {

    @Test
    @DisplayName("it should be able to instance a new student object")
    public void testSuccessStudentInstantiation(){
        Student student = new Student(UUID.randomUUID(), "Gustavo", "de Almeida", "gustavo@gmail.com", "1234", StudentRole.USER);

        Assert.assertNotNull(student.getId());
    }
}
