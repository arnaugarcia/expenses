package com.autentia.web.rest;

import com.autentia.service.expense.ExpenseService;
import com.autentia.service.expense.dto.ExpenseDTO;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import org.slf4j.Logger;

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

}
