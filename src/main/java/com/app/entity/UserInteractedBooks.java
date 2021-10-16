package com.app.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * model that represents books to which user is interacting
 *
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Document
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserInteractedBooks {

    @MongoId(FieldType.STRING)
    private String id;

    @Indexed
    private String userId;

    @Indexed
    private String bookId;

    private String name;

    private String description;

    private String publishedDate;

    private List<String> authorNames;

    private String coverUrl;

    private String readingStatus;

    private int rating;

    public UserInteractedBooks(){
        this.id = new ObjectId().toString();
    }
}
