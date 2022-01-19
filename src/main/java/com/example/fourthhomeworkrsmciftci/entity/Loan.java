package com.example.fourthhomeworkrsmciftci.entity;


import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LOAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;

    @Column(name ="loan_creation_date")
    private LocalDate loanCreationDate;

    // 2 loan; principalDebt & latePaymentInterest
    // paid by the one payment
    @OneToOne
    @JoinColumn(name ="payment_id")
    private Payment payment;

    @Column(name = "loan_amount", updatable = false)
    @Digits(integer = 20,fraction = 2)
    private BigDecimal loanAmount;

    @Column(name = "unpaid_loan_amount")
    @Digits(integer = 20,fraction = 2)
    private BigDecimal unpaidLoanAmount;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    // multiple payments cannot be created that's why 1To1 relationship
    // principal_debt_id should be null for principal debts
    @OneToOne
    @JoinColumn(name = "principal_debt_id")
    private Loan loan;

}