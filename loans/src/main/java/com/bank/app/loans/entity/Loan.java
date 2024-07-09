package com.bank.app.loans.entity;

import com.bank.app.loans.constant.LoanTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String personalId;

    @Column(unique = true)
    private String loanIdentification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanTypeEnum loanType;

    @Column(nullable = false)
    private BigDecimal totalLoan;

    @Column(nullable = false)
    private BigDecimal amountPaid;

    @Column(nullable = false)
    private BigDecimal outstandingAmount;

}
