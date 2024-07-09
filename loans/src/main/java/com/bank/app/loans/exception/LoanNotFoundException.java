package com.bank.app.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String fieldName, String fieldValue) {
        super("Loan not found with the given %s: %s".formatted(fieldName, fieldValue));
    }
}
