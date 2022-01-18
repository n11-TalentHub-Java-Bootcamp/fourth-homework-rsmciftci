package com.example.fourthhomeworkrsmciftci.dao;


import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanDao extends JpaRepository<Loan,Long> {

    List<Loan> findLoanByLoanCreationDateBetweenAndLoanTypeIs(LocalDate earlierDate, LocalDate laterDate, LoanType loanType);

    List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThan(Long id, BigDecimal unpaidLoanAmountGreaterThan);

    List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndPaymentDueDateBefore(Long id, BigDecimal unpaidLoanAmountGreaterThan, LocalDate localDateNow);
}