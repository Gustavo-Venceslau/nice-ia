package com.galmv_.niceia.student.studentService;

import com.galmv_.niceia.domain.student.services.FindByIdStudentService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.enums.StudentRole;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TestFindByIdStudentService extends UnitTestFactory {

    @Autowired
    private FindByIdStudentService findByIdStudentService;

    @Test
    @DisplayName("it should able to find a user by id if the user exists!")
    public void testSuccessFindById(){
        Student studentToGetId = studentRepository
                .save(new Student(null, "meida", "vence", "meidavence@mail.com", "321", StudentRole.USER));

        Student student = findByIdStudentService.execute(studentToGetId.getId());
        Assert.assertNotNull(student.getId());
    }

    @Test
    @DisplayName("it should not to be able to find a user by id if does not exist")
    public void testFailFindById(){
        Assert.assertThrows(UserNotFoundException.class, () -> findByIdStudentService.execute
                (new UUID(0, 0)));
    }

}
