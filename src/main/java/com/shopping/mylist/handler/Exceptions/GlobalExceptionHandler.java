package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;
import com.shopping.mylist.handler.ResponseStatus;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseStatus responseError(String message, HttpStatus status){
        ResponseStatus error = new ResponseStatus();
        error.setStatus("error");
        error.setDetails(message);
        error.setStatusCode(status.value());
        return error;
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request){
        if(e.getClass().isAssignableFrom(UndeclaredThrowableException.class)){
            UndeclaredThrowableException ex = (UndeclaredThrowableException)e;
            return handleGlobalException((BusinessException)ex.getUndeclaredThrowable(), request);
        }else{
            String message = messageSource.getMessage("error.server", new Object[]{e.getMessage()}, Locale.forLanguageTag("pt"));
            ResponseStatus error = new ResponseStatus();
            error.setStatus("error");
            error.setDetails(message);
            error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @ExceptionHandler
    private ResponseEntity<Object> handleGlobalException(BusinessException ex, WebRequest request){
        ResponseStatus error = new ResponseStatus();
        error.setStatus("error");
        error.setDetails(ex.getMessage());
        error.setStatusCode(HttpStatus.CONFLICT.value());
        return handleExceptionInternal(ex, error, headers(), HttpStatus.CONFLICT, request);
    }
}
