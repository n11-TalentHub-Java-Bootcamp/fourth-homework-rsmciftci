package com.example.fourthhomeworkrsmciftci.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class PaymentTransactionDto {
    Long principalDebtId;
    Long customerId;
    @Digits(integer = 20,fraction = 2)
    BigDecimal moneyBeingPaid;
}
