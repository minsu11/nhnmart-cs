package com.nhnacademy.springmvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

//    @ExceptionHandler(ValidationFailedException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleValidationFailedException(ValidationFailedException ex, Model model) {
//        log.error("Validation failed", ex);
//        model.addAttribute("exception", ex);
//        return "error";
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        log.error("Internal server error", ex);
        model.addAttribute("exception", ex);
        return "error";
    }


}

