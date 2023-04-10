package com.autentia.repository.impl;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
