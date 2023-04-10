package com.autentia.repository;

import com.autentia.domain.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    List<Group> findAll();

    Optional<Group> findById(Long id);

    Group save(Group group);
}
