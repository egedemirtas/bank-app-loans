package com.bank.app.loans.dto;

import com.bank.app.loans.constant.LoanTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequestDto {
    @NotEmpty(message = "Personal id can not be empty")
    @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
    private String personalId;

    @NotNull
    private LoanTypeEnum loanType;

    @Positive(message = "Loan amount should be greater than 0")
    private BigDecimal loanAmount;
}
