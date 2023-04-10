package com.autentia.service.group.impl;

import com.autentia.domain.Group;
import com.autentia.domain.User;
import com.autentia.repository.GroupRepository;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.group.exception.GroupNotFoundException;
import com.autentia.service.group.mapper.GroupMapper;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
            .map(groupMapper::toDTO)
            .toList();
    }

    @Override
    public GroupDTO findById(Long id) {
        return groupMapper.toDTO(findGroupById(id));
    }

    private Group findGroupById(Long id) {
        return groupRepository.findById(id)
            .orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public GroupDTO addUser(Long groupId, User user) {
        Group group = findGroupById(groupId);
        group.addUser(user);
        return groupMapper.toDTO(groupRepository.save(group));
    }
}
