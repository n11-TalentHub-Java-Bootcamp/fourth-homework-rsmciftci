package com.example.fourthhomeworkrsmciftci.dto;

import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class LoanDto {

    private Long id;
    private Long customerId;
    private Long paymentId;
    private Long principalDebtId;
    private LocalDate paymentDueDate;
    private LocalDate loanCreationDate;
    private BigDecimal unpaidLoanAmount;
    private BigDecimal loanAmount;
    private BigDecimal interest;
    private LoanType loanType;
}
