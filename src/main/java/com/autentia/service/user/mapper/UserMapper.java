package com.autentia.service.user.mapper;

import com.autentia.domain.User;
import com.autentia.service.user.dto.UserDTO;
import jakarta.inject.Singleton;

@Singleton
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurnames(), user.getLogin());
    }

}
