package com.autentia.service.expense.mapper;

import com.autentia.domain.Expense;
import com.autentia.domain.User;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.request.ExpenseRequest;
import jakarta.inject.Singleton;

import java.time.format.DateTimeFormatter;

import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Singleton
public class ExpenseMapper {

    public ExpenseDTO toDto(Expense expense) {
        DateTimeFormatter formatter = ofPattern("yyyy-MM-dd HH:mm:ss");
        String cratedDate = expense.getDate().format(formatter);

        User user = expense.getUser();
        ExpenseDTO.ExpenseUser expenseUser = new ExpenseDTO.ExpenseUser(user.getName(), user.getSurnames(), user.getLogin());

        return new ExpenseDTO(expense.getName(), expense.getDescription(), expense.getAmount(), cratedDate, expenseUser);
    }
}
