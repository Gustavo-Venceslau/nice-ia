package com.galmv_.niceia.comment;

import com.galmv_.niceia.TestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestCommentClass extends TestFactory {

    @Test
    @DisplayName("should to be able to instance a new comment")
    public void testSuccessInstantiateComment(){
        Assert.assertNotNull(comment);
    }
}
