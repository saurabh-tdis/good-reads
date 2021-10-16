package com.app.repository;

import com.app.entity.Books;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
@Repository
public interface BooksRepository extends ReactiveMongoRepository<Books,String> {
    Mono<Boolean> existsByBookIdIn(List<String> keysList);

    Flux<Books> findByBookIdIn(List<String> bookIds);
}
