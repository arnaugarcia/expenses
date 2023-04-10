package com.autentia.service.expense.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ExpenseDTO(String name, String description, Float amount, String date, ExpenseUser creator) {
    @Serdeable
    public record ExpenseUser(String name, String surname, String login) {
    }

}
