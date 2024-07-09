package com.bank.app.loans.validator;

import com.bank.app.loans.constant.LoanTypeEnum;
import com.bank.app.loans.exception.LoanAlreadyExistsException;
import com.bank.app.loans.repository.LoanRepository;
import org.springframework.stereotype.Component;

@Component
public class LoanValidator {

    private final LoanRepository loanRepository;

    public LoanValidator(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void validateDuplicateLoan(String personalId, LoanTypeEnum loanType) {
        loanRepository.findByPersonalIdAndLoanType(personalId, loanType).ifPresent(s -> {
            throw new LoanAlreadyExistsException("A loan with the same type is created for the same person");
        });
    }
}
