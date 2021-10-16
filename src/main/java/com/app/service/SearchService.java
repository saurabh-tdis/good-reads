package com.app.service;

import com.app.entity.Books;
import reactor.core.publisher.Flux;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */
public interface SearchService {

    Flux<Books> getSearchResult(String query, int pageNo);
}
