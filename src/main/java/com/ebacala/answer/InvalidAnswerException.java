package com.ebacala.answer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAnswerException extends RuntimeException {
    public InvalidAnswerException() {
        super("All unknowns must have different values");
    }
}
