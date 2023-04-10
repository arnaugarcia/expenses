package com.autentia.service.user.request;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotBlank;

@Serdeable
public record UserRequest(@NotBlank String name, @NotBlank String surnames, @NotBlank String login) {
}
