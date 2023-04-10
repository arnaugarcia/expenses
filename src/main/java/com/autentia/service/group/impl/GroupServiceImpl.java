package com.autentia.service.group.impl;

import com.autentia.domain.Group;
import com.autentia.repository.GroupRepository;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
            .map(toGroupDTO())
            .toList();
    }

    private static Function<Group, GroupDTO> toGroupDTO() {
        return group -> new GroupDTO(group.id(), group.getName(), group.getDescription());
    }
}
