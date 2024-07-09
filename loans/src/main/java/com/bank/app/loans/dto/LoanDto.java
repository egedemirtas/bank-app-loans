package com.bank.app.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanDto {
    @NotEmpty(message = "Personal id can not be empty")
    @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
    private String personalId;

    @NotEmpty(message = "Loan ID can not be empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan id should be 15 digits")
    private String loanIdentification;

    @NotEmpty(message = "Loan type can not be empty")
    private String loanType;

    @Positive(message = "Total loan should be a positive number")
    private BigDecimal totalLoan;

    @PositiveOrZero(message = "Amount paid can be equal to or greater than 0")
    private BigDecimal amountPaid;

    @PositiveOrZero(message = "Outstanding can be equal to or greater than 0")
    private BigDecimal outstandingAmount;
}
