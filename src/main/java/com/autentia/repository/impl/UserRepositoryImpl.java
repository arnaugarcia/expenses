package com.autentia.repository.impl;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @ReadOnly
    public Optional<User> findByLogin(String login) {
        return ofNullable(entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
            .setParameter("login", login)
            .getSingleResult());
    }

    @Override
    @ReadOnly
    public List<User> findByGroupId(Long groupId) {
        return entityManager.createQuery("SELECT u from User u inner join u.groups g where g.id = :groupId", User.class)
            .setParameter("groupId", groupId)
            .getResultList();
    }
}
