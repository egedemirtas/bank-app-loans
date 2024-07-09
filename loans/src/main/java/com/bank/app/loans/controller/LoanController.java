package com.bank.app.loans.controller;

import com.bank.app.loans.dto.LoanDto;
import com.bank.app.loans.dto.LoanRequestDto;
import com.bank.app.loans.dto.ResponseDto;
import com.bank.app.loans.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {

    private final ILoanService iLoanService;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createLoan(@RequestBody @Valid LoanRequestDto loanRequestDto) {
        String loanId = iLoanService.createLoan(loanRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Loan created successfully: %s".formatted(loanId)));
    }

    @GetMapping
    public ResponseEntity<LoanDto> getLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
            String personalId) {
        LoanDto loanDto = iLoanService.getLoan(personalId);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto) {
        iLoanService.updateLoan(loanDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
            String personalId) {
        iLoanService.deleteLoan(personalId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
