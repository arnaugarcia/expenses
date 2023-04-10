package com.autentia.web.rest;

import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Get("/groups")
    public List<GroupDTO> findAllGroups() {
        log.info("REST request to get all Groups");
        return groupService.findAll();
    }


}
