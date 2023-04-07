package com.autentia;

import com.autentia.domain.User;
import com.autentia.repository.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get()
    public List<User> users() {
        return userRepository.findAll();
    }
}
