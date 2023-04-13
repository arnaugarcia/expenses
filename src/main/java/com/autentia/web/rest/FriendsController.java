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

import static org.slf4j.LoggerFactory.getLogger;

@Controller("/api")
public class FriendsController {

    private final Logger log = getLogger(FriendsController.class);

    private final FriendService friendService;

    public FriendsController(FriendService friendService) {
        this.friendService = friendService;
    }

    @Post("/groups/{id}/friends")
    public HttpResponse<GroupDTO> addFriendToGroup(@PathVariable("id") Long groupId, @Valid @Body UserRequest userRequest) {
        log.info("REST request to add friend to group: {}", groupId);
        return HttpResponse.ok().body(friendService.addFriendToGroup(groupId, userRequest));
    }

    @Get("/groups/{id}/friends")
    public HttpResponse<List<UserDTO>> findFriendsByGroupId(@PathVariable("id") Long groupId) {
        log.info("REST request to find friends by group: {}", groupId);
        return HttpResponse.ok().body(friendService.findFriendsByGroupId(groupId));
    }

}
