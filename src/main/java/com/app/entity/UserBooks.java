package com.app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Builder
@AllArgsConstructor
public class UserBooks {

    @AllArgsConstructor
    static class UserBookPrimaryKey{
        private String userId;
        private String bookId;
    }

    @Id
    private UserBookPrimaryKey userBookPrimaryKey;

    private LocalDate startedDate;

    private LocalDate completedDate;

    private String readingStatus;

    private int rating;

}
