package com.galmv_.niceia.domain.student.controllers;

import com.galmv_.niceia.domain.student.StudentDTO;
import com.galmv_.niceia.domain.student.exceptions.UserNotFoundException;
import com.galmv_.niceia.domain.student.services.StudentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student/{id}")
@RequiredArgsConstructor
@Slf4j
public class StudentUpdateController {

    private final StudentUpdateService studentUpdateService;

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody StudentDTO studentNewData){
        try {
            this.studentUpdateService.execute(id, studentNewData);

            return ResponseEntity.noContent().build();
        }
        catch (UserNotFoundException e){
            log.info("Exception :" + e);

            return ResponseEntity.notFound().build();
        }
    }
}
