package com.galmv_.niceia.student;

import com.galmv_.niceia.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

    public Student findById(UUID id){
        Optional<Student> optionalStudent = this.repository.findById(id);

        if(optionalStudent.isEmpty()){
            throw new UserNotFoundException("User not founded!");
        }

        return optionalStudent.get();
    }

    public void update(UUID id, StudentDTO data){
        Student studentExists = findById(id);

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

    public void delete(UUID id){
        Student student = findById(id);

        if(student == null){
            throw new UserNotFoundException("User not founded");
        }

        this.repository.delete(student);
    }
}
