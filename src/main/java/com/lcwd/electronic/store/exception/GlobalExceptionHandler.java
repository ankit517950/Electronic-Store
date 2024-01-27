package com.lcwd.electronic.store.exception;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //handler resouurce not found exception
    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        logger.info("Exception Handler Invoked !!");
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<ApiResponseMessage>(responseMessage,HttpStatus.OK);

    }
    //Method argument not valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,Object> response = new HashMap<>();
        allErrors.stream().forEach(i->{
           String message= i.getDefaultMessage();
           String field= ((FieldError)i).getField();
            response.put(field,message);
        });
         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    //Handle Bad Api exception
    @ExceptionHandler(BadAPIRequest.class)
    public ResponseEntity<ApiResponseMessage> handleBadAPIRequest(BadAPIRequest ex)
    {
        logger.info("Bad API request !!");
        ApiResponseMessage responseMessage=ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
        return new ResponseEntity<ApiResponseMessage>(responseMessage,HttpStatus.BAD_REQUEST);

    }
}
