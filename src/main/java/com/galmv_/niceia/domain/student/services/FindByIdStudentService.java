package com.galmv_.niceia.domain.student.services;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindByIdStudentService {

    private final StudentRepository repository;

    public Student execute(UUID id){
        Optional<Student> optionalStudent = this.repository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not founded!");
        }

        return optionalStudent.get();
    }
}
