package com.bank.app.loans.mapper;

import com.bank.app.loans.dto.LoanDto;
import com.bank.app.loans.entity.Loan;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LoanMapper {
    LoanDto loan2LoanDto(Loan loan);

    Loan loanDto2Loan(LoanDto loanDto);

}
