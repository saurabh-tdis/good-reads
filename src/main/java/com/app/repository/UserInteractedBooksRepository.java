package com.app.repository;

import com.app.entity.Books;
import com.app.entity.UserInteractedBooks;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface UserInteractedBooksRepository extends ReactiveMongoRepository<UserInteractedBooks,String> {
    Flux<UserInteractedBooks> findByUserId(String userId);

    Mono<Books> findByUserIdAndBookId(String userId, String bookId);
}
