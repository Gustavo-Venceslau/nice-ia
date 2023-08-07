package com.galmv_.niceia.reaction.reactionService;

import com.galmv_.niceia.domain.reaction.services.FindAllByStudentReactionService;
import com.galmv_.niceia.testFactories.UnitTestFactory;
import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TestFindAllByStudentReactionService extends UnitTestFactory {

    @Autowired
    private FindAllByStudentReactionService findAllByStudentReactionService;

    @Test
    @DisplayName("should to be possible to find a reaction by Student")
    public void testSuccessFindAllByStudent(){
        List<Reaction> reactions = this.findAllByStudentReactionService.execute(student.getId());

        Assert.assertFalse(reactions.isEmpty());
    }

    @Test
    @DisplayName("should not to be possible to find a reaction by Student if student not exists")
    public void testFailFindAllByStudent(){
        Assert.assertThrows(UserNotFoundException.class, () -> this.findAllByStudentReactionService.execute(new UUID(0, 0)));
    }
}
