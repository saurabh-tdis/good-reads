package com.app.dto;

import com.app.entity.ActiveStatus;
import com.app.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 *
 * model that represents users
 *
 * @author Saurabh Vaish
 * @Date 12-09-2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String email;

    private String password;

    private String gender;

    private String active = ActiveStatus.ACTIVE.name();

}
