package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.PaymentDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTotalPaidInterestDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.service.PaymentService;
import com.example.fourthhomeworkrsmciftci.service.transactionService.PaymentTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments/")
@AllArgsConstructor
public class PaymentController {

    private PaymentTransactionService paymentTransactionService;
    private PaymentService paymentService;

    // (4a)
    @Operation(summary = "Paying A Single Loan with Its Interest")
    @ApiResponse(responseCode = "500",description="if DebtId & CustomerId Doesn't Match")
    @ApiResponse(responseCode = "500",description="if Money Being Paid Is Not Equal To Loan Plus The Loan Interest")
    @ApiResponse(responseCode = "400",description="if Money Has Got More Than Two Digits After Decimal Point")
    @PostMapping("")
    public void payLoanOfUser(@Valid @RequestBody PaymentTransactionDto paymentTransactionDto){
        paymentTransactionService.payLoanOfUser(paymentTransactionDto);
    }

    // (4b)
    @Operation(summary = "Paying A Single Loan with Its Interest")
    @ApiResponse(responseCode = "500",description="if payment due date is not in the form YYYY-MM-DD")
    @ApiResponse(responseCode = "500",description="Every 4 year, january has got 28 days, so 2022-02-29 shouldn't be given as an input ")
    @GetMapping("between-dates-{date1}&{date2}")
    public ResponseEntity findPaymentsBetweenDates(@PathVariable("date1") String date1, @PathVariable("date2") String date2){
        List<PaymentDto> paymentDtoList = paymentService.findPaymentsByDatesBetween(date1,date2);
        if(paymentDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(paymentDtoList);
        }

    }

    // (4c)
    @Operation(summary = "Returns All Payments Of The User")
    @ApiResponse(responseCode = "404",description="if user is nonexistent or not have payment")
    @GetMapping("all-by-user/{id}")
    public ResponseEntity findAllPaymentsOfUser(@PathVariable("id") Long id){
        List<PaymentDto> paymentDtoList = paymentService.findAllPaymentsOfUser(id);
        if(paymentDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(paymentDtoList);
        }

    }

    // (4d)
    @Operation(summary = "Returns Sum Of Late Payment Interest Paid By User")
    @GetMapping("sum-of-late-payment-interest-paid-by-user/{id}")
    public BigDecimal findSumOfInterestPaidByUser(@PathVariable("id") Long id){
        BigDecimal totalPaidInterestDto = paymentService.findSumOfInterestPaidByUser(id);
        return totalPaidInterestDto;
    }

}
