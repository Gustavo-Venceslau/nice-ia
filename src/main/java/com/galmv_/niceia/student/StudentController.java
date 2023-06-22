package com.galmv_.niceia.student;

import com.galmv_.niceia.student.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> findAll(){
        Set<Student> students = new HashSet<>(service.findAll());
        Set<StudentDTO> studentDTOS = new HashSet<>(students.stream().map(student -> new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getUsername(),
                student.getPassword()
        )).toList());

        return ResponseEntity.ok().body(studentDTOS);
    }
}
