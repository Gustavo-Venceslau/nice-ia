package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.UnitTestFactory;
import com.galmv_.niceia.student.Student;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import java.util.Set;

public class TestFindAllStudentService extends UnitTestFactory {

    @Test
    @DisplayName("Test if find all methods retrieve all students")
    public void testSuccessFindAllStudents(){
        Set<Student> studentsList = this.studentService.findAll();
        Assert.assertFalse(studentsList.isEmpty());
    }

    @Test
    @DisplayName("it should not possible to findAll if the students does not exist")
    public void testFailFindAllStudents(){
        reactionRepository.deleteAll();
        commentRepository.deleteAll();
        postRepository.deleteAll();
        studentRepository.deleteAll();
        Assert.assertThrows(UserNotFoundException.class, () -> this.studentService.findAll());
    }

}
