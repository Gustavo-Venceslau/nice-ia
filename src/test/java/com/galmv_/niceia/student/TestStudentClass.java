package com.galmv_.niceia.student;

import com.galmv_.niceia.TestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestStudentClass extends TestFactory {

    @Test
    @DisplayName("it should be able to instance a new student object")
    public void testSuccessStudentInstantiation(){
        Assert.assertNotNull(student.getId());
    }
}
