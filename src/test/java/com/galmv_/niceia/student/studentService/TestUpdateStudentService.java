package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.UnitTestFactory;
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

public class TestUpdateStudentService extends UnitTestFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("it should to able to update a existing user")
    public void testSuccessUpdate(){
        Student student = new Student(null ,"gu", "almeida", "gualmeida@mail.com", passwordEncoder.encode("123456"), StudentRole.USER);

        studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        this.studentService.update(student.getId(), studentDTO);

        Assert.assertEquals("gua@mail.com", this.studentService.findById(student.getId()).getEmail());
    }

    @Test
    @DisplayName("it should not to be able to update a user whether does exists!")
    public void testFailUpdate(){
        StudentDTO studentDTO = new StudentDTO("gu", "almeida", "gua@mail.com", passwordEncoder.encode("123456"));

        Assert.assertThrows(UserNotFoundException.class, () -> this.studentService.update(new UUID(0, 0), studentDTO));
    }

}
