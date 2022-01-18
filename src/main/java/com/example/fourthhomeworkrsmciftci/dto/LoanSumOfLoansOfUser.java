package com.example.fourthhomeworkrsmciftci.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanSumOfLoansOfUser {

    BigDecimal sumOfPrincipleLoan;
    BigDecimal sumOfLatePaymentInterest;
    BigDecimal total;
}
