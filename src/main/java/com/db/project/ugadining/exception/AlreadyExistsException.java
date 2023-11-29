package com.db.project.ugadining.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String name) {
        super("'" + name + "' already exists.");
    }
}
