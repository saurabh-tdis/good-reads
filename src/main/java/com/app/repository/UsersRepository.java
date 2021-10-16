package com.app.repository;

import com.app.entity.Books;
import com.app.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface UsersRepository extends ReactiveMongoRepository<Users, String> {
    Mono<Users> findByEmail(String email);

}
