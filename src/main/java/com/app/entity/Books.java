package com.app.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

/**
 *
 * model that represents books
 *
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {

    @MongoId
    @Indexed
    private String bookId;

    private String name;

    private String description;

    private String publishedDate;

    private List<String> coverIds;

    private List<String> authorNames;

    private List<String> authorIds;

    private String coverUrl;

    private List<String> languages;

    private Integer editionCount;

    private List<String> isBnNo;

    private Double averageRating;

    private List<UserComments> comment;

    @Indexed
    private Integer publishedYear;


}
