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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Saving Loan")
    @ApiResponse(responseCode = "400",description="Bad Request caused by nonexistent customer")
    @ApiResponse(responseCode = "400",description="if payment due date is not in the form YYYY-MM-DD")
    @ApiResponse(responseCode = "500",description="If loanAmount includes more than 2 digits after decimal point")
    @PostMapping("")
    public ResponseEntity save(@RequestBody LoanSavingDto loanSavingDto){
        LoanDto loanDto = loanService.save(loanSavingDto);
        return ResponseEntity.ok(loanDto);

    }

    // (3d)
    @Operation(summary = "Finds All Principle Loans Created Between two dates")
    @ApiResponse(responseCode = "500",description="if payment due date is not in the form YYYY-MM-DD")
    @ApiResponse(responseCode = "500",description="Every 4 year, january has got 28 days, so 2022-02-29 shouldn't be given as an input ")
    @GetMapping("between-{beginningDate}-and-{endingDate}")
    public ResponseEntity findPrincipalLoansCreatedBetween(@PathVariable("beginningDate") String beginningDate, @PathVariable("endingDate") String endingDate){

        LocalDate beginningLocalDate = LocalDate.parse(beginningDate);
        LocalDate endingLocalDate = LocalDate.parse(endingDate);
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> loanDtoList = loanService.findLoanByLoanCreationDateBetweenAndLoanTypeIs(beginningLocalDate,endingLocalDate,loanType);

        return ResponseEntity.ok(loanDtoList);
    }

    // (3e) returns unpaid principle loans
    @Operation(summary = "Returns All Unpaid Loans Of An User")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have overdue unpaid loan")
    @GetMapping("unpaid-loans-of-user/{id}")
    public ResponseEntity findLoansOfUser(@PathVariable("id") Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> principleLoansOfUser = loanService.findPrincipleLoansOfUser(id, loanType);
        if(principleLoansOfUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(principleLoansOfUser);
        }

    }

    // (3f) returns unpaid principle loans
    @Operation(summary = "Returns All Overdue Unpaid Loans Of An User")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have overdue unpaid loan")
    @GetMapping("overdue-unpaid-loans-of-user/{id}")
    public ResponseEntity findOverdueUnpaidLoansOfUser(@PathVariable("id") Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;

        List<LoanDto> principleLoansOfUser = loanService.findOverduePrincipleLoansOfUser(id,loanType);
        if(principleLoansOfUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(principleLoansOfUser);
        }

    }

    // (3e)
    @Operation(summary = "Returns Total Of Principle Loan, Interest, and Total Of Both")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have unpaid loan")
    @GetMapping("sum-of-unpaid-loans-of-user/{id}")
    public ResponseEntity findSumOfUnpaidLoansOfUser(@PathVariable("id") Long id){

        List<Loan> loanListOfUser = loanService.calculateSumOfUnpaidLoansOfUser(id);
        if(loanListOfUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            LoanSumOfLoansOfUser sumOfLoansOfUser = LoanConverter.converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(loanListOfUser);
            return ResponseEntity.ok(sumOfLoansOfUser);
        }

    }

    // (3h)
    @Operation(summary = "Returns Total Of Overdue Principle Loan, Their Interest, and Total Of Both")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have unpaid loan")
    @GetMapping("sum-of-overdue-unpaid-loans-of-user/{id}")
    public ResponseEntity calculateSumOfOverdueUnpaidLoansOfUser(@PathVariable("id") Long id){
        List<Loan> overdueLoanListOfUser = loanService.calculateSumOfOverdueUnpaidLoansOfUser(id);
        if(overdueLoanListOfUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            LoanSumOfLoansOfUser sumOfLoansOfUser = LoanConverter.converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(overdueLoanListOfUser);
            return ResponseEntity.ok(sumOfLoansOfUser);
        }
    }
    // (3i)
    @Operation(summary = "Only Return Total Interest")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have overdue debt")
    @GetMapping("total-interest-of-user/{id}")
    public ResponseEntity calculateTotalInterestOfUser(@PathVariable("id") Long id){

        List<Loan> overdueLoanListOfUser = loanService.calculateTotalInterestOfUser(id);
        if(overdueLoanListOfUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            LoanTotalInterestDto totalInterest = LoanConverter.converLoanListOfUserToLoanTotalInterestDto(overdueLoanListOfUser);
            return ResponseEntity.ok(totalInterest);
        }

    }

}
