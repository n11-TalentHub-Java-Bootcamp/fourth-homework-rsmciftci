package com.example.fourthhomeworkrsmciftci.dto;

import com.example.fourthhomeworkrsmciftci.entity.Customer;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class PaymentDto {

    private Long id;
    private LocalDate paymentDate;
    private Long loanId;
    private Long customerId;
}
