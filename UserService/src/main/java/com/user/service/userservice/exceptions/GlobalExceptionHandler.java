package com.user.service.userservice.exceptions;

import com.user.service.userservice.response.ApiResponse;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        // when ResourceNotFoundException is generated then this function is called automatically;
        logger.info("Exceptional Handler Invoke!!!");
        ApiResponse apiResponseMessage = new ApiResponse() ;
        apiResponseMessage.setMessage(ex.getMessage());
        apiResponseMessage.setCode(HttpStatus.NOT_FOUND);
        apiResponseMessage.setSuccess(true);
        return new ResponseEntity<ApiResponse>(apiResponseMessage,HttpStatus.NOT_FOUND);
    }
}
