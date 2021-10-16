package com.app.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchKeys {

    @MongoId(FieldType.STRING)
    private String id;

    @Indexed
    private String searchKeyWithPageNo;

    private List<String> bookIds;

    private LocalDate searchDate;

}
