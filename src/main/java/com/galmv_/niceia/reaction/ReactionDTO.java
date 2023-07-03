package com.galmv_.niceia.reaction;

import com.galmv_.niceia.reaction.Enums.ComponentType;
import com.galmv_.niceia.reaction.Enums.Type;
import com.galmv_.niceia.student.Student;

import java.util.UUID;

public record ReactionDTO(Type type, ComponentType componentType, UUID componentId, Student student) {
}
