package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.domain.student.services.StudentDeleteService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class TestStudentDeleteService extends UnitTestFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentDeleteService studentDeleteService;
    @Test
    @DisplayName("it should to be able to delete a existing student")
    public void testSuccessDelete(){
        Student student = new Student(null ,"gu", "almeida", "gualmeida@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        this.studentRepository.save(student);

        this.studentDeleteService.execute(student.getId());

        Assert.assertTrue(this.studentRepository.findById(student.getId()).isEmpty());
    }

    @Test
    @DisplayName("it should not to be able to delete a student nonexisting student")
    public void testFailDelete(){
        Assert.assertThrows(UserNotFoundException.class,() -> this.studentDeleteService.execute(new UUID(0, 0)));
    }
}
