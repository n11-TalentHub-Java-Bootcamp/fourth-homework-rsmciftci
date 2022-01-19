package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.converter.LoanConverter;
import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSumOfLoansOfUser;
import com.example.fourthhomeworkrsmciftci.dto.LoanTotalInterestDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.interestCalculator.InterestCalculator;
import com.example.fourthhomeworkrsmciftci.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/loans/")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;


    // (3a)
    @PostMapping("")
    public ResponseEntity save(@RequestBody LoanSavingDto loanSavingDto){
        LoanDto loanDto = loanService.save(loanSavingDto);
        return ResponseEntity.ok(loanDto);

    }

    // (3d)
    @GetMapping("between-{beginningDate}-and-{endingDate}")
    public ResponseEntity findPrincipalLoansCreatedBetween(@PathVariable("beginningDate") String beginningDate, @PathVariable("endingDate") String endingDate){

        LocalDate beginningLocalDate = LocalDate.parse(beginningDate);
        LocalDate endingLocalDate = LocalDate.parse(endingDate);
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> loanDtoList = loanService.findLoanByLoanCreationDateBetweenAndLoanTypeIs(beginningLocalDate,endingLocalDate,loanType);

        return ResponseEntity.ok(loanDtoList);
    }

    // (3e) returns unpaid principle loans
    @GetMapping("unpaid-loans-of-user/{id}")
    public ResponseEntity findLoansOfUser(@PathVariable("id") Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> principleLoansOfUser = loanService.findPrincipleLoansOfUser(id, loanType);
        return ResponseEntity.ok(principleLoansOfUser);
    }

    // (3f) returns unpaid principle loans
    @GetMapping("overdue-unpaid-loans-of-user/{id}")
    public ResponseEntity findOverdueUnpaidLoansOfUser(@PathVariable("id") Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> principleLoansOfUser = loanService.findOverduePrincipleLoansOfUser(id,loanType);
        return ResponseEntity.ok(principleLoansOfUser);
    }

    // (3e)
    @GetMapping("sum-of-unpaid-loans-of-user/{id}")
    public ResponseEntity findSumOfUnpaidLoansOfUser(@PathVariable("id") Long id){

        LoanSumOfLoansOfUser sumOfUnpaidLoansOfUserDto = loanService.calculateSumOfUnpaidLoansOfUser(id);
        return ResponseEntity.ok(sumOfUnpaidLoansOfUserDto);
    }

    // (3h)
    @GetMapping("sum-of-overdue-unpaid-loans-of-user/{id}")
    public ResponseEntity calculateSumOfOverdueUnpaidLoansOfUser(@PathVariable("id") Long id){
        LoanSumOfLoansOfUser sumOfUnpaidLoansOfUserDto = loanService.calculateSumOfOverdueUnpaidLoansOfUser(id);
        return ResponseEntity.ok(sumOfUnpaidLoansOfUserDto);
    }
    // (3i)
    @GetMapping("total-interest-of-user/{id}")
    public ResponseEntity calculateTotalInterestOfUser(@PathVariable("id") Long id){

        LoanTotalInterestDto totalInterestDto = loanService.calculateTotalInterestOfUser(id);
        return ResponseEntity.ok(totalInterestDto);
    }

}
