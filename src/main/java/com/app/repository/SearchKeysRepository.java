package com.app.repository;

import com.app.entity.Books;
import com.app.entity.SearchKeys;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
@Repository
public interface SearchKeysRepository extends ReactiveMongoRepository<SearchKeys,String> {

    Mono<SearchKeys> findBySearchKeyWithPageNo(String s);
}
