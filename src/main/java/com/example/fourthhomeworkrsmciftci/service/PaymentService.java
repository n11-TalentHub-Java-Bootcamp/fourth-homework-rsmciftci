package com.example.fourthhomeworkrsmciftci.service;

import com.example.fourthhomeworkrsmciftci.converter.PaymentMapper;
import com.example.fourthhomeworkrsmciftci.dao.PaymentDao;
import com.example.fourthhomeworkrsmciftci.dto.PaymentDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTotalPaidInterestDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentDao paymentDao;

    public List<PaymentDto> findPaymentsByDatesBetween(String dateOne, String dateTwo){
        LocalDate date1 = LocalDate.parse(dateOne);
        LocalDate date2 = LocalDate.parse(dateTwo);
        LocalDate earlierDate;
        LocalDate laterDate;
        if(date1.isBefore(date2)){
            earlierDate = date1;
            laterDate = date2;
        }else{
            earlierDate = date2;
            laterDate = date1;
        }

        List<Payment> paymentList = paymentDao.findAllByPaymentDateBetween(earlierDate,laterDate);
        PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
        List<PaymentDto> paymentDtoList = paymentMapper.convertPaymentListToPaymentDtoList(paymentList);
        return  paymentDtoList;
    }

    public List<PaymentDto> findAllPaymentsOfUser(Long id){
        List<Payment> paymentList = paymentDao.findPaymentsByCustomer_Id(id);
        PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
        List<PaymentDto> paymentDtoList = paymentMapper.convertPaymentListToPaymentDtoList(paymentList);
        return  paymentDtoList;

    }


    public BigDecimal findSumOfInterestPaidByUser(Long id){
        return paymentDao.calculateSumOfInterestOfUser(id);
    }
}
