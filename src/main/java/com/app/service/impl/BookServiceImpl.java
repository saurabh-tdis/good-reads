package com.app.service.impl;

import com.app.entity.Books;
import com.app.entity.UserInteractedBooks;
import com.app.exception.CustomRuntimeException;
import com.app.repository.BooksRepository;
import com.app.repository.UserInteractedBooksRepository;
import com.app.service.BookService;
import com.app.util.BookUrls;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;

    private final UserInteractedBooksRepository userInteractedBooksRepository;

    @Override
    public Mono<Books> getBookById(String bookId) {
        log.info("getting book from book id = "+bookId);
        return booksRepository.findById(bookId).map(book -> {
            if(!BookUrls.NO_IMAGE.equals(book.getCoverUrl())){
                book.setCoverUrl(book.getCoverUrl().replace("-M","-L" ));
            }
            return book;
        });
    }

    @Override
    public Flux<UserInteractedBooks> getUserInteractedBooksByUserId(String userId) {
        return this.userInteractedBooksRepository.findByUserId(userId).switchIfEmpty( Mono.error(new CustomRuntimeException("No books found with given user id")));
    }

    @Override
    public Mono<Books> getBookByUserId(String bookId, String userId) {
        if(!StringUtils.hasText(userId)){
            return Mono.error(new CustomRuntimeException("User id can not be empty"));
        }
        if(!StringUtils.hasText(bookId)){
            return Mono.error(new CustomRuntimeException("Book id can not be empty"));
        }
        return this.userInteractedBooksRepository.findByUserIdAndBookId(userId,bookId).switchIfEmpty( Mono.error(new CustomRuntimeException("No books found with given user id")));
    }

}
