package com.example.fourthhomeworkrsmciftci.dto;

import com.example.fourthhomeworkrsmciftci.entity.Customer;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanSavingDto {



    private Long customerId;
    private LocalDate paymentDueDate;
    private BigDecimal loanAmount;


}
