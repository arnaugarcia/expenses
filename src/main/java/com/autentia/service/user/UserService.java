package com.autentia.service.user;

import com.autentia.domain.User;
import com.autentia.service.user.request.UserRequest;

public interface UserService {

    User createUser(UserRequest userRequest);

}
