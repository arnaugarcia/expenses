package com.autentia.service.expense;

import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.dto.ExpenseSummaryDTO;
import com.autentia.service.expense.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {

    /**
     * Get all the expenses of a group.
     * @param groupId id of the group
     * @return the list of expenses.
     */
    List<ExpenseDTO> findExpensesByGroup(Long groupId);

    /**
     * Create a new expense.
     * @param groupId id of the group
     * @param expenseRequest expense to create
     * @return the expense DTO.
     */
    ExpenseDTO createExpense(Long groupId, ExpenseRequest expenseRequest);

    /**
     * Get expense summary by group.
     * @param groupId id of the group
     * @return the list of expense summary DTOs.
     */
    List<ExpenseSummaryDTO> getExpenseSummaryByGroup(Long groupId);
}
