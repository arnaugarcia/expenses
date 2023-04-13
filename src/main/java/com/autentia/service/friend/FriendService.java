package com.autentia.service.friend;

import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;

import java.util.List;

public interface FriendService {

    /**
     * Add friend to group.
     * @param groupId id of the group
     * @param userRequest user to add
     * @return the group DTO.
     */
    GroupDTO addFriendToGroup(Long groupId, UserRequest userRequest);

    /**
     * Get all the friends by group.
     * @param groupId id of the group
     * @return the list of friends.
     */
    List<UserDTO> findFriendsByGroupId(Long groupId);
}
