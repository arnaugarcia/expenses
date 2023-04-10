package com.autentia.service.user;

import com.autentia.service.user.dto.UserDTO;
import com.autentia.service.user.request.UserRequest;

public interface UserService {

    UserDTO createUser(UserRequest userRequest);

}
