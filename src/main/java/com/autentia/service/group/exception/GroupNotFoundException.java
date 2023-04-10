package com.autentia.service.group.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(Long id) {
        super("Could not find group with id" + id);
    }
}
