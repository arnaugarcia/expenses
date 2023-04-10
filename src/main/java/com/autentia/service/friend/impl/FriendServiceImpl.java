package com.autentia.service.friend.impl;

import com.autentia.service.friend.FriendService;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.UserService;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;
import jakarta.inject.Singleton;

@Singleton
public class FriendServiceImpl implements FriendService {

    private final GroupService groupService;

    private final UserService userService;

    public FriendServiceImpl(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    public GroupDTO addFriendToGroup(Long groupId, UserRequest userRequest) {
        GroupDTO group = groupService.findById(groupId);
        UserDTO user = userService.createUser(userRequest);
        groupService.addUser(group.id(), user.id());
        return group;
    }
}
