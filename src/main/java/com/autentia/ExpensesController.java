package com.autentia;

import io.micronaut.http.annotation.*;

@Controller("/expenses")
public class ExpensesController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}