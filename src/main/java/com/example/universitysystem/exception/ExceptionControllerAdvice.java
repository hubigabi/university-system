package com.example.universitysystem.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {StudentNotFoundException.class, TeacherNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        log.error("Entity with this id does not exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with this id does not exist");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(Exception e) {
        log.error("Validation failed for entity");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed for entity");
    }

}
