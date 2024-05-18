package com.ebacala.solution;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidSolutionException extends RuntimeException {
    public InvalidSolutionException() {
        super("All unknowns must have different values");
    }
}
