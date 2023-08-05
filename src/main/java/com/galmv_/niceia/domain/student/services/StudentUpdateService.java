package com.galmv_.niceia.domain.student.services;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentDTO;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentUpdateService {

    private final FindByIdStudentService findByIdStudentService;
    private final StudentRepository repository;

    public void execute(UUID id, StudentDTO data){
        Student studentExists = findByIdStudentService.execute(id);

        if(studentExists == null){
            throw new UserNotFoundException("User not exists!");
        }

        updateStudentData(studentExists, data);

        repository.save(studentExists);
    }

    private void updateStudentData(Student studentToUpdate, StudentDTO data) {
        studentToUpdate.setFirstName(data.firstName());
        studentToUpdate.setLastName(data.lastName());
        studentToUpdate.setEmail(data.email());
        studentToUpdate.setPassword(data.password());
    }
}
