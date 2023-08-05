package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.domain.student.services.FindByIdStudentService;
import com.galmv_.niceia.domain.student.services.StudentUpdateService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.student.StudentDTO;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class TestStudentUpdateService extends UnitTestFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentUpdateService updateStudentService;

    @Autowired
    private FindByIdStudentService findByIdStudentService;

    @Test
    @DisplayName("it should to able to update a existing user")
    public void testSuccessUpdate(){
        Student student = new Student(null ,"gu", "almeida", "gualmeida@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        this.updateStudentService.execute(student.getId(), studentDTO);

        Assert.assertEquals("gua@mail.com", this.findByIdStudentService.execute(student.getId()).getEmail());
    }

    @Test
    @DisplayName("it should not to be able to update a user whether does exists!")
    public void testFailUpdate(){
        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        Assert.assertThrows(UserNotFoundException.class, () -> this.updateStudentService.execute(new UUID(0, 0), studentDTO));
    }

}
