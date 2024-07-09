package com.bank.app.loans.service;

import com.bank.app.loans.dto.LoanDto;
import com.bank.app.loans.dto.LoanRequestDto;

public interface ILoanService {

    /**
     * Creates loan with the given personalId and loan type and loan amount
     *
     * @param loanRequestDto
     */
    String createLoan(LoanRequestDto loanRequestDto);

    /**
     * Fetch LoanDto by personal id
     * @param personalId
     * @return
     */
    LoanDto getLoan(String personalId);

    /**
     * Updates loan
     * @param loanDto
     */
    void updateLoan(LoanDto loanDto);

    /**
     * Deletes loan with personalId
     * @param personalId
     */
    void deleteLoan(String personalId);
}
