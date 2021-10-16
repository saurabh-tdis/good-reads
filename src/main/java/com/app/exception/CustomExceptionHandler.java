package com.app.exception;

import com.app.dto.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author Saurabh Vaish
 * @Date 18-09-2021
 */

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    public static Mono<ApiResponse> handleException(String message){
//        handleCustomRuntimeException(exception);
        return Mono.just(new ApiResponse(HttpStatus.BAD_REQUEST.value(), null, message));
    }

    @ExceptionHandler(value = CustomRuntimeException.class)
    public Mono<ResponseEntity<ApiResponse>> handleCustomRuntimeException(CustomRuntimeException exception) {
        ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), null, exception.getMessage());
        log.error("result: {}", exception.getMessage());
        return Mono.just(new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_GATEWAY));
    }

}
