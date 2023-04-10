package com.autentia.service.expense.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ExpenseSummaryDTO(String name, String description, List<ExpenseUser> userExpenses) {

    @Serdeable
    public record ExpenseUser(String name, String surname, String login, Float amount) {
    }

}
