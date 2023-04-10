package com.autentia.web.rest;

import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.request.ExpenseRequest;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller("/api")
public class ExpenseController {

    private static final Logger log = getLogger(ExpenseController.class);

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Get("/groups/{id}/expenses")
    public List<ExpenseDTO> findAllExpensesByGroup(@PathVariable(name = "id") Long groupId) {
        log.info("REST request to get all Expenses by Group");
        return expenseService.findExpensesByGroup(groupId);
    }

    @Post("/groups/{id}/expenses")
    public ExpenseDTO createExpense(@PathVariable(name = "id") Long groupId, @Valid @Body ExpenseRequest expenseRequest) {
        log.info("REST request to create Expense");
        return expenseService.createExpense(groupId, expenseRequest);
    }

}
