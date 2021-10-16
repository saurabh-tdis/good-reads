package com.app.service.impl;

import com.app.entity.Users;
import com.app.exception.CustomRuntimeException;
import com.app.repository.UsersRepository;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Override
    public Mono<Users> createUser(Users users) {
        if(!StringUtils.hasText(users.getEmail())){
            return Mono.error(new CustomRuntimeException("User email can not be empty"));
        }
        if(!StringUtils.hasText(users.getName())){
            return Mono.error(new CustomRuntimeException("User name can not be empty"));
        }

        return this.getUserByEmail(users.getEmail()).flatMap(u->{
            if(u.getId()==null){
                return this.usersRepository.save(u);
            }else{
               return Mono.error(new CustomRuntimeException("User already exist with given name"));
            }
        });
    }

    @Override
    public Mono<Users> getUserByUserId(String userId) {
        return usersRepository.findById(userId).switchIfEmpty( Mono.error(new CustomRuntimeException("User not found with given user id")));
    }

    @Override
    public Mono<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email).switchIfEmpty( Mono.error(new CustomRuntimeException("User not found with given email")));
    }
}
