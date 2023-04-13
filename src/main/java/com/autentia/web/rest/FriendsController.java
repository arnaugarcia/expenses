package com.autentia.web.rest;

import com.autentia.service.friend.FriendService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;

import java.util.List;

import static io.micronaut.http.HttpResponse.ok;
import static org.slf4j.LoggerFactory.getLogger;

@Controller("/api")
public class FriendsController {

    private final Logger log = getLogger(FriendsController.class);

    private final FriendService friendService;

    public FriendsController(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * {@code POST  /groups/{id}/friends} : add friend to group.
      * @param groupId id of the group
     * @param userRequest user to add
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the new group
     */
    @Post("/groups/{id}/friends")
    public HttpResponse<GroupDTO> addFriendToGroup(@PathVariable("id") Long groupId, @Valid @Body UserRequest userRequest) {
        log.info("REST request to add friend to group: {}", groupId);
        return ok().body(friendService.addFriendToGroup(groupId, userRequest));
    }

    /**
     * {@code GET  /groups/{id}/friends} : get all the friends by group.
     * @param groupId id of the group
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the new group
     */
    @Get("/groups/{id}/friends")
    public HttpResponse<List<UserDTO>> findFriendsByGroupId(@PathVariable("id") Long groupId) {
        log.info("REST request to find friends by group: {}", groupId);
        return ok().body(friendService.findFriendsByGroupId(groupId));
    }

}
