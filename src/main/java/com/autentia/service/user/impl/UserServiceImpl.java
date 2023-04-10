package com.autentia.service.user.impl;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import com.autentia.service.user.UserService;
import com.autentia.service.user.exception.UserNotFoundException;
import com.autentia.service.user.request.UserRequest;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(UserRequest userRequest) {
        User user = new User()
            .name(userRequest.name())
            .surnames(userRequest.surnames())
            .login(userRequest.login());
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
    }

}
