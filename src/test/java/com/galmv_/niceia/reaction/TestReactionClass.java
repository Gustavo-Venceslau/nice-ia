package com.galmv_.niceia.reaction;

import com.galmv_.niceia.TestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestReactionClass extends TestFactory {

    @Test
    @DisplayName("should to be possible to instanciate a Reaction object")
    public void testSuccessReactionInstantiation(){
        Assert.assertNotNull(reaction.getId());
    }
}
