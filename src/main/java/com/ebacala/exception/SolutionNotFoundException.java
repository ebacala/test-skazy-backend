package com.ebacala.exception;

public class SolutionNotFoundException extends RuntimeException {

    public SolutionNotFoundException(Long pId) {
        super("Could not find solution " + pId);
    }
}
