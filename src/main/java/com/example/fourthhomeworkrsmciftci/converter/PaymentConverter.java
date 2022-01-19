package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.entity.Customer;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PaymentConverter {

    public PaymentConverter() {
    }

    public static Payment convertPaymentTransactionDtoToPaymentEntity(PaymentTransactionDto paymentTransactionDto){
        Payment payment = new Payment();

        Customer customer = new Customer();
        customer.setId(paymentTransactionDto.getCustomerId());

        Loan loan = new Loan();
        loan.setId(paymentTransactionDto.getPrincipalDebtId());


        payment.setPaymentDate(LocalDate.now());
        payment.setCustomer(customer);
        payment.setLoan(loan);

        return payment;
    }
}
