package com.galmv_.niceia.post;

import com.galmv_.niceia.TestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestClassPost extends TestFactory {

    @Test
    @DisplayName("it should be able to instance a new Post object")
    public void testSuccessPostInstantiation(){
        Assert.assertNotNull(post.getId());
    }
}
