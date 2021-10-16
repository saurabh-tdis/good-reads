package com.app.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * model that represents users
 *
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Document
@Getter
@Setter
@AllArgsConstructor
public class Users {

    @MongoId(FieldType.STRING)
    private String id;

    private String name;

    @Indexed
    private String email;

    private String password;

    private Gender gender;

    private String active = ActiveStatus.ACTIVE.name();

    public Users(){
        this.id = new ObjectId().toString();
    }

}
