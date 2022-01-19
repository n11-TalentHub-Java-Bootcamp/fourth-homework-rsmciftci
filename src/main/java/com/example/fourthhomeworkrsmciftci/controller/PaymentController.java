package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.PaymentDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTotalPaidInterestDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.service.PaymentService;
import com.example.fourthhomeworkrsmciftci.service.transactionService.PaymentTransactionService;
import lombok.AllArgsConstructor;
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
    @PostMapping("")
    public void payLoanOfUser(@Valid @RequestBody PaymentTransactionDto paymentTransactionDto){
        paymentTransactionService.payLoanOfUser(paymentTransactionDto);
    }

    // (4b)
    @GetMapping("between-dates-{date1}&{date2}")
    public List<PaymentDto> findPaymentsBetweenDates(@PathVariable("date1") String date1,@PathVariable("date2") String date2){
        List<PaymentDto> paymentDtoList = paymentService.findPaymentsByDatesBetween(date1,date2);
        return paymentDtoList;
    }

    // (4c)
    @GetMapping("all-by-user/{id}")
    public List<PaymentDto> findAllPaymentsOfUser(@PathVariable("id") Long id){
        List<PaymentDto> paymentDtoList = paymentService.findAllPaymentsOfUser(id);
        return paymentDtoList;
    }

    // (4d)
    @GetMapping("sum-of-late-payment-interest-paid-by-user/{id}")
    public BigDecimal findSumOfInterestPaidByUser(@PathVariable("id") Long id){
        BigDecimal totalPaidInterestDto = paymentService.findSumOfInterestPaidByUser(id);
        return totalPaidInterestDto;
    }

}
