package com.galmv_.niceia.domain.student;

import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> findAll(){
        try {
            Set<Student> students = new HashSet<>(service.findAll());
            Set<StudentDTO> studentDTOS = new HashSet<>(students.stream().map(student -> new StudentDTO(
                    student.getFirstName(),
                    student.getLastName(),
                    student.getUsername(),
                    student.getPassword()
            )).toList());

            return ResponseEntity.ok().body(studentDTOS);
        }
        catch (UserNotFoundException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") UUID id){
        try {
            Student student = this.service.findById(id);

            return ResponseEntity.ok().body(student);
        }
        catch (UserNotFoundException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody StudentDTO studentNewData){
        try {
            this.service.update(id, studentNewData);

            return ResponseEntity.noContent().build();
        }
        catch (UserNotFoundException e){
            System.out.println("Exception :" + e);

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.service.delete(id);

            return ResponseEntity.noContent().build();
        }
        catch (UserNotFoundException e){
            System.out.println("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
