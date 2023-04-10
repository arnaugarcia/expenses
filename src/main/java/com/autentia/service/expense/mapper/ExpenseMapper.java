package com.autentia.service.expense.mapper;

import com.autentia.domain.Expense;
import com.autentia.domain.User;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.request.ExpenseRequest;
import jakarta.inject.Singleton;

import static java.time.ZonedDateTime.now;

@Singleton
public class ExpenseMapper {

    public ExpenseDTO toDto(Expense expense) {
        String cratedDate = expense.getDate().toLocalDateTime().toString();

        User user = expense.getUser();
        ExpenseDTO.ExpenseUser expenseUser = new ExpenseDTO.ExpenseUser(user.getName(), user.getSurnames(), user.getLogin());

        return new ExpenseDTO(expense.getName(), expense.getDescription(), expense.getAmount(), cratedDate, expenseUser);
    }

    public Expense toEntity(ExpenseRequest expenseRequest) {
        return new Expense().name(expenseRequest.name())
            .description(expenseRequest.description())
            .amount(expenseRequest.amount())
            .date(now());
    }
}
