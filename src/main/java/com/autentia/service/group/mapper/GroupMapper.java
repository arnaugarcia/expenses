package com.autentia.service.group.mapper;

import com.autentia.domain.Group;
import com.autentia.service.group.dto.GroupDTO;
import jakarta.inject.Singleton;

@Singleton
public class GroupMapper {

    public GroupDTO toDTO(Group group) {
        return new GroupDTO(group.getId(), group.getName(), group.getDescription());
    }
}
