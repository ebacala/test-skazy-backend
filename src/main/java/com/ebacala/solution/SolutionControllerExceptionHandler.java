package com.ebacala.solution;

import com.ebacala.responsehelper.JsonMessage;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SolutionControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PSQLException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDuplicateKey(PSQLException exception) {
        if (exception.getMessage().contains("duplicate key value violates unique constraint")) {
            JsonMessage jsonMessage = new JsonMessage("This solution already exist in the database.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonMessage.getBody());
        }

        return ResponseEntity.internalServerError().body("Unknown error");
    }
}
