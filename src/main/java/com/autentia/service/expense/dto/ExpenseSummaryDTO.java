package com.autentia.service.expense.dto;

import com.autentia.service.user.dto.UserDTO;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ExpenseSummaryDTO(String name, String description, List<ExpenseUser> userExpenses) {

    @Serdeable
    public record ExpenseUser(UserDTO payer, Float amount, UserDTO payee) {
    }

}
