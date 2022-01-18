package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.interestCalculator.InterestCalculator;
import com.example.fourthhomeworkrsmciftci.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/loans/")
@AllArgsConstructor
@NoArgsConstructor
public class LoanController {

    private LoanService loanService;
    private InterestCalculator interestCalculator;

    // (3a)
    @PostMapping("")
    public ResponseEntity save(@RequestBody LoanSavingDto loanSavingDto){
        LoanDto loanDto = loanService.save(loanSavingDto);
        return ResponseEntity.ok(loanDto);

    }

    // (3d)
    @GetMapping("between-{beginningDate}-and-{endingDate}")
    public ResponseEntity findPrincipalLoansCreatedBetween(@PathVariable String beginningDate, @PathVariable String endingDate){

        LocalDate beginningLocalDate = LocalDate.parse(beginningDate);
        LocalDate endingLocalDate = LocalDate.parse(endingDate);
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> loanDtoList = loanService.findLoanByLoanCreationDateBetweenAndLoanTypeIs(beginningLocalDate,endingLocalDate,loanType);

        return ResponseEntity.ok(loanDtoList);
    }

    // (3e) returns unpaid principle loans
    @GetMapping("unpaid-loans-of-user/{id}")
    public ResponseEntity findLoansOfUser(@PathVariable Long id){

        List<LoanDto> principleLoansOfUser = loanService.findPrincipleLoansOfUser(id);
        return ResponseEntity.ok(principleLoansOfUser);
    }

    // (3f) returns unpaid principle loans
    @GetMapping("overdue-unpaid-loans-of-user/{id}")
    public ResponseEntity findOverdueUnpaidLoansOfUser(@PathVariable Long id){

        List<LoanDto> principleLoansOfUser = loanService.findOverduePrincipleLoansOfUser(id);
        return ResponseEntity.ok(principleLoansOfUser);





    }

}
