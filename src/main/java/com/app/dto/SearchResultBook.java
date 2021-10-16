package com.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResultBook {
    private String key;
    private String title;
    private int edition_count;
    private List<String> publish_date;
    private int first_publish_year;
    private List<String> isbn;
    private String cover_i;
    private List<String> language;
    private List<String> author_name;
}
