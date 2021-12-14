package com.bridgelabz.exception.custom;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Purpose : This class is created for catching validation related exception
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@RestControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {

    /**
     * Purpose : This method is used to respond back the exception from the user end
     *
     * @param ex      : exception which will generated from program if any input is invalid
     * @param headers : if there is any header which will also comes from application
     * @param status  : this is the Http status
     * @param request : this is the web request to specify which request is to be caught
     * @return the new response entity which holds the time stamp, status code and the details of exception message
     */
    @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
    (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> objectMap = new LinkedHashMap<>();
        objectMap.put("timestamp", new Date());
        objectMap.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        objectMap.put("errors", errors);
        return new ResponseEntity<>(objectMap, headers, status);
    }
}
