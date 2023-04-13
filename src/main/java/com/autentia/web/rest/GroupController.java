package com.autentia.web.rest;

import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller("/api")
public class GroupController {

    private static final Logger log = getLogger(GroupController.class);

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * {@code GET  /groups} : get all the groups.
     *
     * @return the {@link List} of Groups.
     */
    @Get("/groups")
    public List<GroupDTO> findAllGroups() {
        log.info("REST request to get all Groups");
        return groupService.findAll();
    }


}
