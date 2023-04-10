package com.autentia.repository.impl;

import com.autentia.domain.Group;
import com.autentia.repository.GroupRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class GroupRepositoryImpl implements GroupRepository {

    private final EntityManager entityManager;

    public GroupRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public List<Group> findAll() {
        return entityManager.createQuery("select g from Group g", Group.class).getResultList();
    }
}
