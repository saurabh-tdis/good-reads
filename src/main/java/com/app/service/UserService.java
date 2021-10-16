package com.app.service;

import com.app.entity.Users;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface UserService {
    Mono<Users> createUser(Users users);

    Mono<Users> getUserByUserId(String userId);

    Mono<Users> getUserByEmail(String email);
}
