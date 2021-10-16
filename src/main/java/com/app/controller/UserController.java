package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.dto.UserDto;
import com.app.entity.ActiveStatus;
import com.app.entity.Gender;
import com.app.entity.Users;
import com.app.exception.CustomExceptionHandler;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 18-09-2021
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping(value = "/register-user/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> registerUser(@RequestBody UserDto users){
        if(!StringUtils.hasText(users.getName()))return CustomExceptionHandler.handleException("Please provide valid name");
        if(!StringUtils.hasText(users.getEmail()))return CustomExceptionHandler.handleException("Please provide valid Email");
        if(!StringUtils.hasText(users.getPassword()))return CustomExceptionHandler.handleException("Please provide valid password");
        if(users.getGender()==null)return CustomExceptionHandler.handleException("Please provide valid Gender");

        Users user = new Users();
        user.setName(users.getName());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        user.setGender(users.getGender().equalsIgnoreCase("male")? Gender.MALE:Gender.FEMALE);
        user.setActive(ActiveStatus.ACTIVE.name());
       return this.userService.createUser(user).map(u -> {
            UserDto userDto = new UserDto();
            userDto.setId(u.getId());
            userDto.setName(u.getName());
            userDto.setEmail(u.getEmail());
            userDto.setGender(u.getGender().name());
            userDto.setActive(ActiveStatus.ACTIVE.name());
            return userDto;
        }).doOnSuccess(dto-> Mono.just(new ApiResponse(HttpStatus.OK.value(), dto,"Success")));
    }

//    @PostMapping("/login")
//    public Mono<ApiResponse> login(@RequestBody Users users){
//        if(!StringUtils.hasText(users.getEmail()))return CustomExceptionHandler.handleException("Please provide valid Email");
//        if(!StringUtils.hasText(users.getPassword()))return CustomExceptionHandler.handleException("Please provide valid password");
//
//        Mono<Users> user = this.userService.getUserByEmail(users.getEmail());
//
//        user.map()
//
//        return this.userService.getUserByUserId(userId)
//                .map(b->new ApiResponse(HttpStatus.OK.value(),b,"Success" ))
//                .switchIfEmpty(CustomExceptionHandler.handleException("No User Found"));
//
//    }
//

    @GetMapping("/get-user/{userId}")
    public Mono<ApiResponse> getBookByUserId(@PathVariable String userId){
        return this.userService.getUserByUserId(userId)
                .map(b->new ApiResponse(HttpStatus.OK.value(),b,"Success" ))
                .switchIfEmpty(CustomExceptionHandler.handleException("No User Found"));
    }

}
