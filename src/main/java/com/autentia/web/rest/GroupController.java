package com.autentia.web.rest;

import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Get("/groups")
    public String groups() {
        log.info("REST request to get all Groups");
        return "groups";
    }


}
