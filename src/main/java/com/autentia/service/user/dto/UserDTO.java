package com.autentia.service.user.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UserDTO(Long id, String name, String surnames, String login) {
}
