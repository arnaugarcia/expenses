package com.autentia.repository.impl;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import java.util.Optional;

import static java.util.Optional.ofNullable;

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

    @Override
    public Optional<User> findByLogin(String login) {
        return ofNullable(entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
            .setParameter("login", login)
            .getSingleResult());
    }
}
