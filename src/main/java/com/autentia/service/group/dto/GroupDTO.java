package com.autentia.service.group.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GroupDTO(Long id, String name, String description) {
}
