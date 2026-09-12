package com.example.Category_Service.Exception;

import com.example.Category_Service.DTO.ErrorDetailsDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        System.out.print("Method argument valid not");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex){
        System.out.print("Business exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> HandleNotFound(ResourceNotFoundException e , WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }












    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDTO> GenericException(Exception e , WebRequest webRequest){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO();
        errorDetailsDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetailsDTO.setDateTime(LocalDateTime.now());
        errorDetailsDTO.setMsg("Something went wrong");
        errorDetailsDTO.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}