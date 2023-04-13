package com.autentia.web.rest;

import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import com.autentia.service.expense.dto.ExpenseSummaryDTO;
import com.autentia.service.expense.request.ExpenseRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.List;

import static io.micronaut.http.HttpResponse.created;
import static org.slf4j.LoggerFactory.getLogger;

@Controller("/api")
public class ExpenseController {

    private static final Logger log = getLogger(ExpenseController.class);

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * {@code GET  /groups/{id}/expenses} : get all the expenses by group.
     * @param groupId id of the group
     * @return the {@link List} of Expenses.
     */
    @Get("/groups/{id}/expenses")
    public List<ExpenseDTO> findAllExpensesByGroup(@PathVariable(name = "id") Long groupId) {
        log.info("REST request to get all Expenses by Group");
        return expenseService.findExpensesByGroup(groupId);
    }

    /**
     * {@code POST  /groups/{id}/expenses} : create a new expense.
     * @param groupId id of the group
     * @param expenseRequest expense to create
     * @return the {@link ExpenseDTO} with status {@code 201 (Created)} and with body the new expense
     */
    @Post("/groups/{id}/expenses")
    public HttpResponse<ExpenseDTO> createExpense(@PathVariable(name = "id") Long groupId, @Valid @Body ExpenseRequest expenseRequest) {
        log.info("REST request to create Expense");
        return created(expenseService.createExpense(groupId, expenseRequest));
    }

    /**
     * {@code GET  /groups/{id}/summary} : get expense summary by group.
     * @param groupId id of the group
     * @return the {@link List} of ExpenseSummaryDTO.
     */
    @Get("/groups/{id}/summary")
    public List<ExpenseSummaryDTO> getExpenseSummary(@PathVariable(name = "id") Long groupId) {
        log.info("REST request to get Expense Summary");
        return expenseService.getExpenseSummaryByGroup(groupId);
    }

}
