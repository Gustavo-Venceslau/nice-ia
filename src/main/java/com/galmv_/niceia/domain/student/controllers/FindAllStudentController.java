package com.galmv_.niceia.domain.student.controllers;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.StudentDTO;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.domain.student.services.FindAllStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class FindAllStudentController {

    private final FindAllStudentService findAllStudentService;

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> handle(){
        try {
            Set<Student> students = new HashSet<>(findAllStudentService.execute());
            Set<StudentDTO> studentDTOS = new HashSet<>(students.stream().map(student -> new StudentDTO(
                    student.getFirstName(),
                    student.getLastName(),
                    student.getUsername(),
                    student.getPassword()
            )).toList());

            return ResponseEntity.ok().body(studentDTOS);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
