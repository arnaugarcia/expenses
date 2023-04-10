package com.autentia.repository;

import com.autentia.domain.Expense;

import java.util.List;

public interface ExpenseRepository {

    List<Expense> findExpensesByGroup(Long groupId);

}
