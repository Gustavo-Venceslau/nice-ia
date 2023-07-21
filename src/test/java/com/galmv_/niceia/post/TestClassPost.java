package com.galmv_.niceia.post;

import com.galmv_.niceia.shared.testFactories.UnitTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestClassPost extends UnitTestFactory {

    @Test
    @DisplayName("it should be able to instance a new Post object")
    public void testSuccessPostInstantiation(){
        Assert.assertNotNull(post.getId());
    }
}
