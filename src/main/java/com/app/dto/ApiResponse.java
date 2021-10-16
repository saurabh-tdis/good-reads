package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Saurabh Vaish
 * @Date 18-09-2021
 */

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private int statusCode;
    private Object payload;
    private String message;

    public ApiResponse(int statusCode, Object payload, String message) {
        this.statusCode = statusCode;
        this.payload = payload;
        this.message = message;
    }
}
