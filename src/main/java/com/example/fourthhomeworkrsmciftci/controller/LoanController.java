package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @GetMapping("")
   public ResponseEntity findPrincipalLoansCreatedBetween(){

        LocalDate date1 = LocalDate.of(2022,05,28);
        LocalDate date2 = LocalDate.of(2012,05,28);
        LoanType loanType = LoanType.PRINCIPAL_DEBT;
        List<LoanDto> loanDtoList = loanService.findLoanByLoanCreationDateBetweenAndLoanTypeIs(date1,date2,loanType);

        return ResponseEntity.ok(loanDtoList);
   }

}
