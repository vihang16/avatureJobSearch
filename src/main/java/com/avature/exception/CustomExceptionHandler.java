package com.avature.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value= InvalidJobException.class)
    public ResponseEntity<Map<String, String>> constraintViolationHandler(InvalidJobException ex, HttpResponse<String> response){
      Map<String, String> map = new HashMap<>();
      map.put("message", ex.getMessage());
      map.put("status", "bahut bura hua");
      log.error("received error:"+ex.getMessage());
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> normlExceptionHandler(Exception ex, HttpResponse response){
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", "bahut bura hua");
        log.error("received error:"+ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }*/
}
