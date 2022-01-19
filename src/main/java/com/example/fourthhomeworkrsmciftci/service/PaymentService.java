package com.example.fourthhomeworkrsmciftci.service;

import com.example.fourthhomeworkrsmciftci.dao.PaymentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentDao paymentDao;
}
