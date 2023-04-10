package com.autentia.service.expense.impl;

import com.autentia.repository.ExpenseRepository;
import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.mapper.ExpenseMapper;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    @ReadOnly
    public List<ExpenseDTO> findExpensesByGroup(Long groupId) {
        return expenseRepository.findExpensesByGroupSortedByDateDesc(groupId).stream()
            .map(expenseMapper::toDto)
            .toList();
    }
}
