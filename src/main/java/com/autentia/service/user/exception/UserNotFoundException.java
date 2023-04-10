package com.autentia.service.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super("User with login " + login + " not found");
    }
}
