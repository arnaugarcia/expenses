package com.autentia.repository;

import com.autentia.domain.Group;

import java.util.List;

public interface GroupRepository {
    List<Group> findAll();
}
