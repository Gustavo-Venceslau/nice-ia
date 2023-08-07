package com.galmv_.niceia.domain.reaction.services;

import com.galmv_.niceia.domain.reaction.Reaction;
import com.galmv_.niceia.domain.reaction.ReactionRepository;
import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.services.FindByIdStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByStudentReactionService {

    private final FindByIdStudentService findByIdStudentService;
    private final ReactionRepository repository;

    public List<Reaction> execute(UUID id) {
        Student student = findByIdStudentService.execute(id);

        return repository.findAllByStudent(student);
    }
}
