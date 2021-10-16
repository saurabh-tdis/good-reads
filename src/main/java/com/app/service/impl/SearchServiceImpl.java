package com.app.service.impl;

import com.app.dto.SearchResult;
import com.app.dto.SearchResultBook;
import com.app.entity.Books;
import com.app.entity.SearchKeys;
import com.app.repository.BooksRepository;
import com.app.repository.SearchKeysRepository;
import com.app.service.SearchService;
import com.app.util.BookUrls;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Service
@Log4j2
public class SearchServiceImpl implements SearchService {

    private final WebClient webClient;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private SearchKeysRepository searchKeysRepository;

    public SearchServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configure -> configure.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build()) // WebClient.Builder gets the default 256Kb limit1. However, the following enabled me to raise the buffer size limit to 16M:
                .baseUrl(BookUrls.SEARCH_JSON_URL).build();
    }


    @Override
    public Flux<Books> getSearchResult(String query, int page) {
        return this.searchKeysRepository.findBySearchKeyWithPageNo(query + "::" + page).defaultIfEmpty(new SearchKeys()).flatMapMany(re->{
            if(re.getId()==null || (re.getSearchDate()!=null && re.getSearchDate().isBefore(LocalDate.now()))){
                log.info("getting query result from server");
                return getBooksFluxFromServer(query, page);
            }else{
                log.info("getting data from db");
                return this.booksRepository.findByBookIdIn(re.getBookIds());
            }
        });
    }

    private Flux<Books> getBooksFluxFromServer(String query, int page) {
        List<Books> bookList = new ArrayList<>();
        return this.webClient.get().uri(re->re.queryParam("query", query).queryParam("page", page).build())
                .retrieve().bodyToMono(SearchResult.class)
                .flatMapMany(searchResult->Flux.fromIterable(searchResult.getDocs()))
                .map(sr -> {
                    sr.setKey(sr.getKey().replace("/works/", ""));
                    String coverId = sr.getCover_i();
                    if (StringUtils.hasText(coverId)) {
                        coverId = BookUrls.COVER_IMAGE_URL + coverId + "-M.jpg";
                    } else {
                        coverId = "/images/no-image.png";
                    }
                    sr.setCover_i(coverId);
                    Books book = this.mapBooks(sr);
                    bookList.add(book);
                    return book;
                }).doOnComplete(()->{
                    booksRepository.existsByBookIdIn(bookList.stream().map(Books::getBookId).collect(Collectors.toList())).subscribe(result-> {
                        if(!result) {
                            log.info("data is not present in db ");
                            saveResultInDb(bookList, query, page);
                        }
                    });
                })
                .onErrorStop();
    }

    private void saveResultInDb(List<Books> books,String query, int page) {
        log.info("start saving data into books db ");
//        List<Books> books = bookList.parallelStream().map(this::mapBooks).collect(Collectors.toList());
        booksRepository.saveAll(books).subscribe();

        SearchKeys searchKeys = new SearchKeys();
        searchKeys.setBookIds(books.stream().map(Books::getBookId).collect(Collectors.toList()));
        searchKeys.setSearchDate(LocalDate.now());
        searchKeys.setSearchKeyWithPageNo(query+"::"+page);
        searchKeys.setId(searchKeys.getSearchKeyWithPageNo());
        this.searchKeysRepository.save(searchKeys).subscribe(); // setting id as search keys to replace with new data
        log.info("stop saving data into books db ");
    }

    private Books mapBooks(SearchResultBook resultBook){
        Books books = new Books();
        books.setBookId(resultBook.getKey());
        books.setName(resultBook.getTitle());
        books.setDescription("");
        books.setAuthorNames(resultBook.getAuthor_name());
        books.setCoverUrl(resultBook.getCover_i());
        books.setLanguages(resultBook.getLanguage());
        books.setEditionCount(resultBook.getEdition_count());
        books.setIsBnNo(resultBook.getIsbn());
        books.setPublishedDate(resultBook.getPublish_date().get(0));
        books.setPublishedYear(resultBook.getFirst_publish_year());
        return books;
    }

}
