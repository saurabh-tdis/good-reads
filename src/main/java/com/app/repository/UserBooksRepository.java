package com.app.repository;

import com.app.entity.UserBooks;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface UserBooksRepository extends ReactiveMongoRepository<UserBooks,String> {
}
