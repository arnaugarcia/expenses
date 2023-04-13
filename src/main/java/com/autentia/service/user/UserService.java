package com.autentia.service.user;

import com.autentia.domain.User;
import com.autentia.service.user.request.UserRequest;

import java.util.List;

public interface UserService {

    User createUser(UserRequest userRequest);

    User findByLogin(String login);

    List<User> findByGroupId(Long groupId);
}
