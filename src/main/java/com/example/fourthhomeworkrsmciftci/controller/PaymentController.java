package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.service.PaymentService;
import com.example.fourthhomeworkrsmciftci.service.transactionService.PaymentTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
