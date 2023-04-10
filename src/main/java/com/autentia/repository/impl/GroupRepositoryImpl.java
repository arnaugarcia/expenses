package com.autentia.repository.impl;

import com.autentia.domain.Group;
import com.autentia.repository.GroupRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

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

    @Override
    @ReadOnly
    public Optional<Group> findById(Long id) {
        return ofNullable(entityManager.find(Group.class, id));
    }

    @Override
    @Transactional
    public Group save(Group group) {
        entityManager.persist(group);
        return group;
    }
}
