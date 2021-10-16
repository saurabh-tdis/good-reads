package com.app.service;

import com.app.entity.Books;
import com.app.entity.UserInteractedBooks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface BookService {
    Mono<Books> getBookById(String bookId);

    Flux<UserInteractedBooks> getUserInteractedBooksByUserId(String userId);

    Mono<Books> getBookByUserId(String userId,String bookId);
}
