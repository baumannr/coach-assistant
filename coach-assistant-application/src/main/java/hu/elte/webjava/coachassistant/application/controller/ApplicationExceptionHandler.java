package hu.elte.webjava.coachassistant.application.controller;

import hu.elte.webjava.coachassistant.application.common.Views;
import hu.elte.webjava.coachassistant.application.exception.EntityNotFoundException;
import hu.elte.webjava.coachassistant.application.exception.UnsupportedUserTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UnsupportedUserTypeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCustomException(UnsupportedUserTypeException ex) {
        return Views.ERROR_400;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCustomException(EntityNotFoundException ex) {
        return Views.ERROR_400;
    }
}
