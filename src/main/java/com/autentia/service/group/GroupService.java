package com.autentia.service.group;

import com.autentia.domain.User;
import com.autentia.service.group.dto.GroupDTO;

import java.util.List;

public interface GroupService {

    List<GroupDTO> findAll();

    GroupDTO findById(Long id);

    GroupDTO addUser(Long groupId, User user);

}
