package com.bank.app.loans.exception;

import com.bank.app.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class LoanExceptionHandler {

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public final ErrorResponseDto handleDuplicateCustomerException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public final ErrorResponseDto handleGeneralException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ErrorResponseDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            WebRequest webRequest) {

        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                ex.getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.joining(", ")), LocalDateTime.now());

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ErrorResponseDto handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            WebRequest request) {

        return new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(),
                LocalDateTime.now());

    }
}
