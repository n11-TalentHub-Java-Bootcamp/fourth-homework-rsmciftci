package com.example.fourthhomeworkrsmciftci.dao;

import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    List<Payment> findAllByPaymentDateBetween(LocalDate earlierDate,LocalDate laterDate);
    List<Payment> findPaymentsByCustomer_Id(Long id);

    @Query("SELECT  sum(l.loanAmount)  FROM  Payment p  INNER JOIN Loan l   ON p.customer.id = l.customer.id WHERE  l.loanType='LATE_PAYMENT_INTEREST' AND  p.customer.id = :id")
    BigDecimal calculateSumOfInterestOfUser(Long id);

}
