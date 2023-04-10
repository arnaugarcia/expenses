package com.autentia.repository;

import com.autentia.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByLogin(String login);
}
