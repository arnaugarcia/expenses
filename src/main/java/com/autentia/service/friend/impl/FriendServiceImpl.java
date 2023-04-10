package com.autentia.service.friend.impl;

import com.autentia.domain.User;
import com.autentia.service.friend.FriendService;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.UserService;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
public class FriendServiceImpl implements FriendService {

    private final GroupService groupService;

    private final UserService userService;

    public FriendServiceImpl(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public GroupDTO addFriendToGroup(Long groupId, UserRequest userRequest) {
        GroupDTO group = groupService.findById(groupId);
        User user = userService.createUser(userRequest);
        groupService.addUser(group.id(), user);
        return group;
    }
}
