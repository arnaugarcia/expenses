package com.autentia.service.expense.request;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Serdeable
public record ExpenseRequest(@NotBlank String name, @NotBlank String description, @Min(1) Float amount, @NotEmpty String creatorLogin) {

}
