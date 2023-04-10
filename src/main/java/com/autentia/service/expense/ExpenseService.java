package com.autentia.service.expense;

import com.autentia.service.expense.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDTO> findExpensesByGroup(Long groupId);

}
