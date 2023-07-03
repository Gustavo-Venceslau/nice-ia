package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.TestFactory;
import com.galmv_.niceia.reaction.Reaction;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.UUID;

public class TestFindAllByStudentReactionService extends TestFactory {

    @Test
    @DisplayName("should to be possible to find a reaction by Student")
    public void testSuccessFindAllByStudent(){
        List<Reaction> reactions = this.reactionService.findAllByStudent(student.getId());

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be possible to find a reaction by Student if student not exists")
    public void testFailFindAllByStudent(){
        Assert.assertThrows(UserNotFoundException.class, () -> this.reactionService.findAllByStudent(new UUID(0, 0)));
    }
}
