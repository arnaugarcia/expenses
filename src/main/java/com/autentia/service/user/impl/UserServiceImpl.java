package com.autentia.service.user.impl;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import com.autentia.service.user.UserService;
import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.mapper.UserMapper;
import com.autentia.service.user.request.UserRequest;
import jakarta.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserRequest userRequest) {
        User user = new User()
            .name(userRequest.name())
            .surnames(userRequest.surnames())
            .login(userRequest.login());
        return saveAndMapToDTO(user);
    }

    private UserDTO saveAndMapToDTO(User user) {
        return userMapper.toDTO(userRepository.save(user));
    }
}
