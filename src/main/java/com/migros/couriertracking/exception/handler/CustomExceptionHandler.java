package com.migros.couriertracking.exception.handler;

import com.migros.couriertracking.exception.EntityNotFoundException;
import com.migros.couriertracking.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity handleNotFoundExceptions(Exception ex, WebRequest request) {
        Response response = new Response();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

}