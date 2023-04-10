package com.autentia.service.expense.impl;

import com.autentia.domain.Expense;
import com.autentia.domain.User;
import com.autentia.repository.ExpenseRepository;
import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.mapper.ExpenseMapper;
import com.autentia.service.expense.request.ExpenseRequest;
import com.autentia.service.group.GroupService;
import com.autentia.service.user.UserService;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;

    private final GroupService groupService;

    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ExpenseMapper expenseMapper,
                              GroupService groupService,
                              UserService userService) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    @ReadOnly
    public List<ExpenseDTO> findExpensesByGroup(Long groupId) {
        return expenseRepository.findExpensesByGroupSortedByDateDesc(groupId).stream()
            .map(expenseMapper::toDto)
            .toList();
    }

    @Override
    @Transactional
    public ExpenseDTO createExpense(Long groupId, ExpenseRequest expenseRequest) {
        groupService.findById(groupId); // Check if group exists
        Expense expense = expenseMapper.toEntity(expenseRequest);
        User user = userService.findByLogin(expenseRequest.creatorLogin());

        expense.setUser(user);
        expenseRepository.save(expense);

        groupService.addExpense(groupId, expense);

        return expenseMapper.toDto(expense);
    }
}
