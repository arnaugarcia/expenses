package com.autentia.service.expense.mapper;

import com.autentia.domain.Expense;
import com.autentia.service.expense.dto.ExpenseDTO;
import jakarta.inject.Singleton;

@Singleton
public class ExpenseMapper {

    public ExpenseDTO toDto(Expense expense) {
        String cratedDate = expense.getDate().toLocalDateTime().toString();
        return new ExpenseDTO(expense.getName(), expense.getDescription(), expense.getAmount(), cratedDate);
    }

}
