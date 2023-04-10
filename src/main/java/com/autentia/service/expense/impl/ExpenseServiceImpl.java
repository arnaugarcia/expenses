package com.autentia.service.expense.impl;

import com.autentia.domain.Expense;
import com.autentia.repository.ExpenseRepository;
import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.function.Function;

@Singleton
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    @ReadOnly
    public List<ExpenseDTO> findExpensesByGroup(Long groupId) {
        return expenseRepository.findExpensesByGroup(groupId).stream()
            .map(toExpenseDTO())
            .toList();
    }

    private static Function<Expense, ExpenseDTO> toExpenseDTO() {
        return expense -> new ExpenseDTO(expense.getName(), expense.getDescription(), expense.getAmount(), expense.getDate().toLocalDateTime().toString());
    }
}
