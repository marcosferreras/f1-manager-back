package com.MADG2.controllers;

import com.MADG2.common.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException e) {
        Map<String, Object> responseAsMap = new HashMap<>();
        responseAsMap.put("error", e.getMessage());
        return new ResponseEntity<>(responseAsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Map<String, Object>> handleClassCastException(ClassCastException e) {
        Map<String, Object> responseAsMap = new HashMap<>();
        responseAsMap.put("error", "Unauthorized");
        return new ResponseEntity<>(responseAsMap, HttpStatus.UNAUTHORIZED);
    }
}
