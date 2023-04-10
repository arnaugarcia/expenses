package com.autentia.service.friend;

import com.autentia.domain.User;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.user.request.UserRequest;

public interface FriendService {

    GroupDTO addFriendToGroup(Long groupId, UserRequest userRequest);

}
