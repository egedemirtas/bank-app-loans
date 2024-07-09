package com.bank.app.loans.repository;

import com.bank.app.loans.constant.LoanTypeEnum;
import com.bank.app.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByPersonalIdAndLoanType(String personalId, LoanTypeEnum loanType);

    Optional<Loan> findByPersonalId(String personalId);

    Optional<Loan> findByLoanIdentification(String loanIdentification);
}
