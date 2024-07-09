package com.bank.app.loans.service;

import com.bank.app.loans.dto.LoanDto;
import com.bank.app.loans.dto.LoanRequestDto;
import com.bank.app.loans.entity.Loan;
import com.bank.app.loans.exception.LoanNotFoundException;
import com.bank.app.loans.mapper.LoanMapper;
import com.bank.app.loans.repository.LoanRepository;
import com.bank.app.loans.validator.LoanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class LoanService implements ILoanService {
    private static final Long NEW_LOAN_LIMIT = 100000L;

    private final LoanRepository loanRepository;
    private final LoanValidator loanValidator;
    private final LoanMapper loanMapper;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanValidator loanValidator, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanValidator = loanValidator;
        this.loanMapper = loanMapper;
    }

    @Override
    public String createLoan(LoanRequestDto loanRequestDto) {
        loanValidator.validateDuplicateLoan(loanRequestDto.getPersonalId(), loanRequestDto.getLoanType());
        Loan loan = createNewLoan(loanRequestDto);
        loan = loanRepository.save(loan);
        return loan.getLoanIdentification();
    }

    @Override
    public LoanDto getLoan(String personalId) {
        return loanMapper.loan2LoanDto(getLoanByPersonalId(personalId));
    }

    @Override
    public void updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanIdentification(loanDto.getLoanIdentification())
                .orElseThrow(() -> new LoanNotFoundException("Loan ID", loanDto.getLoanIdentification()));
        Loan updatedLoan = loanMapper.loanDto2Loan(loanDto);
        updatedLoan.setId(loan.getId());
        loanRepository.save(updatedLoan);
    }

    @Override
    public void deleteLoan(String personalId) {
        loanRepository.delete(getLoanByPersonalId(personalId));
    }

    private Loan createNewLoan(LoanRequestDto loanRequestDto) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanIdentification(Long.toString(randomLoanNumber));
        newLoan.setPersonalId(loanRequestDto.getPersonalId());
        newLoan.setLoanType(loanRequestDto.getLoanType());
        newLoan.setTotalLoan(loanRequestDto.getLoanAmount());
        newLoan.setAmountPaid(BigDecimal.ZERO);
        newLoan.setOutstandingAmount(loanRequestDto.getLoanAmount());
        return newLoan;
    }

    private Loan getLoanByPersonalId(String personalId) {
        return loanRepository.findByPersonalId(personalId)
                .orElseThrow(() -> new LoanNotFoundException("personal ID", personalId));
    }
}
