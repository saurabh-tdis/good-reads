package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.exception.CustomExceptionHandler;
import com.app.exception.CustomRuntimeException;
import com.app.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@RestController
@RequestMapping("/api")
@Log4j2
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/get-book/{bookId}")
    public Mono<ApiResponse> getBookById(@PathVariable String bookId){
        return this.bookService.getBookById(bookId)
                .map(b->new ApiResponse(HttpStatus.OK.value(),b,"Success" ))
                .switchIfEmpty(CustomExceptionHandler.handleException("Book Not Found"));
    }

    @GetMapping("/get-user-interacted-books/{userId}")
    public Flux<ApiResponse> getUserInteractedBooksByUserId(@PathVariable String userId){
        return this.bookService.getUserInteractedBooksByUserId(userId)
                .map(b->new ApiResponse(HttpStatus.OK.value(),b,"Success" ))
                .switchIfEmpty(Flux.just(new ApiResponse(HttpStatus.OK.value(), Flux.empty(),"Success")));
    }

    @GetMapping("/get-book/{userId}/{bookId}")
    public Mono<ApiResponse> getBookByUserId(@PathVariable String userId,@PathVariable String bookId){
        return this.bookService.getBookByUserId(userId,bookId)
                .map(b->new ApiResponse(HttpStatus.OK.value(),b,"Success" ))
                .switchIfEmpty(Mono.just(new ApiResponse(HttpStatus.OK.value(), Mono.empty(),"Success")));
    }




}
