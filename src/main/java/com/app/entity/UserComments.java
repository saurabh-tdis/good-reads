package com.app.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 *
 * model that represents comments on book
 *
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Getter
@Setter
@AllArgsConstructor
public class UserComments {

    @MongoId(FieldType.STRING)
    private String id;

    @Indexed
    private String userId;

    private String name;

    private String email;

    private String comment;

    public UserComments(){
        this.id = new ObjectId().toString();
    }
}
