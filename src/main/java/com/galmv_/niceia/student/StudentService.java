package com.galmv_.niceia.student;

import com.galmv_.niceia.student.entities.Student;
import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Set<Student> findAll(){
        Set<Student> students = new HashSet<>(repository.findAll());

        if(students.isEmpty()){
            throw new UserNotFoundException("users not founded");
        }

        return students;
    }
}
