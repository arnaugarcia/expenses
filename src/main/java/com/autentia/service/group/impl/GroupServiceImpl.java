package com.autentia.service.group.impl;

import com.autentia.domain.Group;
import com.autentia.domain.User;
import com.autentia.repository.GroupRepository;
import com.autentia.service.group.GroupService;
import com.autentia.service.group.dto.GroupDTO;
import com.autentia.service.group.exception.GroupNotFoundException;
import com.autentia.service.group.mapper.GroupMapper;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.function.Function;

@Singleton
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    @ReadOnly
    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
            .map(groupMapper::toDTO)
            .toList();
    }

    @Override
    @ReadOnly
    public GroupDTO findById(Long id) {
        return groupRepository.findById(id)
            .map(groupMapper::toDTO)
            .orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public GroupDTO addUser(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
            .map(result -> result.addUser(new User().id(userId)))
            .orElseThrow(() -> new GroupNotFoundException(groupId));
        return groupMapper.toDTO(groupRepository.save(group));
    }
}
