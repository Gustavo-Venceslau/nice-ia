package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.domain.student.services.FindAllStudentService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class TestFindAllStudentService extends UnitTestFactory {

    @Autowired
    private FindAllStudentService findAllStudentService;

    @Test
    @DisplayName("Test if find all methods retrieve all students")
    public void testSuccessFindAllStudents(){
        Set<Student> studentsList = this.findAllStudentService.execute();
        Assert.assertFalse(studentsList.isEmpty());
    }

    @Test
    @DisplayName("it should not possible to findAll if the students does not exist")
    public void testFailFindAllStudents(){
        reactionRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
        studentRepository.deleteAll();
        Assert.assertThrows(UserNotFoundException.class, () -> this.findAllStudentService.execute());
    }

}
