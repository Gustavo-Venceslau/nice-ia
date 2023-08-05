package com.galmv_.niceia.domain.student.controllers;

import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.domain.student.services.StudentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class StudentDeleteController {

    private final StudentDeleteService studentDeleteService;

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        try {
            this.studentDeleteService.execute(id);

            return ResponseEntity.noContent().build();
        }
        catch (UserNotFoundException e){
            log.info("Exception: " + e);

            return ResponseEntity.notFound().build();
        }
    }
}
