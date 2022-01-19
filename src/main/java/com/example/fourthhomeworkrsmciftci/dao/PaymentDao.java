package com.example.fourthhomeworkrsmciftci.dao;

import com.example.fourthhomeworkrsmciftci.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    List<Payment> findAllByPaymentDateBetween(LocalDate earlierDate,LocalDate laterDate);

}
