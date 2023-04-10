package com.autentia.service.expense;

import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.dto.ExpenseSummaryDTO;
import com.autentia.service.expense.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDTO> findExpensesByGroup(Long groupId);

    ExpenseDTO createExpense(Long groupId, ExpenseRequest expenseRequest);

    ExpenseSummaryDTO getExpenseSummaryByGroup(Long groupId);
}
