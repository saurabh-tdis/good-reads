package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.dto.SearchResultBook;
import com.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "/search/{query}/{pageNo}")
    @ResponseBody
    public Mono<ApiResponse> getSearchResults(@PathVariable String query, @PathVariable int pageNo) {
        return this.searchService.getSearchResult(query, pageNo).collectList().map(result-> new ApiResponse(200,result, "Success"));
    }

}
