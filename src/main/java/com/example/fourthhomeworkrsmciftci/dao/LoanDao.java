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


    List<Loan> findLoansByLoanCreationDateBetween(LocalDate earlierDate,LocalDate laterDate);

    List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIs(Long id, BigDecimal unpaidLoanAmountGreaterThan, LoanType loanType);
    List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIsAndPaymentDueDateBefore(Long id, BigDecimal unpaidLoanAmountGreaterThan, LoanType loanType, LocalDate now);

    List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndPaymentDueDateBeforeAndLoanTypeIs(Long id, BigDecimal unpaidLoanAmountGreaterThan, LocalDate localDateNow,LoanType loanType);
}