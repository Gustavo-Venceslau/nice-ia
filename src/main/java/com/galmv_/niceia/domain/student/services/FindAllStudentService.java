package com.galmv_.niceia.domain.student.services;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentRepository;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FindAllStudentService {

    private final StudentRepository repository;

    public Set<Student> execute(){
        Set<Student> students = new HashSet<>(repository.findAll());

        if(students.isEmpty()){
            throw new UserNotFoundException("users not founded");
        }

        return students;
    }
}
