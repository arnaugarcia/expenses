package com.autentia.service.friend;

import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;

import java.util.List;

public interface FriendService {

    GroupDTO addFriendToGroup(Long groupId, UserRequest userRequest);

    List<UserDTO> findFriendsByGroupId(Long groupId);
}
