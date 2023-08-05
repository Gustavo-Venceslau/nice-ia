package com.galmv_.niceia.domain.student.controllers;

import com.galmv_.niceia.domain.student.Student;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.domain.student.services.FindByIdStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class FindByIdStudentController {

    private final FindByIdStudentService findByIdStudentService;

    @GetMapping
    public ResponseEntity<Student> handle(@PathVariable("id") UUID id){
        try {
            Student student = this.findByIdStudentService.execute(id);

            return ResponseEntity.ok().body(student);
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
