package com.autentia.service.expense.dto;

import com.autentia.service.user.dto.UserDTO;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ExpenseSummaryDTO(UserDTO payer, Float amount, UserDTO payee) {
}
