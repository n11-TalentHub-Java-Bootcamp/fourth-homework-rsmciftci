package com.example.fourthhomeworkrsmciftci.service.transactionService;

import com.example.fourthhomeworkrsmciftci.converter.PaymentConverter;
import com.example.fourthhomeworkrsmciftci.converter.PaymentMapper;
import com.example.fourthhomeworkrsmciftci.dao.LoanDao;
import com.example.fourthhomeworkrsmciftci.dao.PaymentDao;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.interestCalculator.InterestCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentTransactionService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private LoanDao loanDao;

    @Transactional
    public void payLoanOfUser(PaymentTransactionDto paymentTransactionDto){

        LocalDate localDateNow = LocalDate.now();
        PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
        Payment payment = PaymentConverter.convertPaymentTransactionDtoToPaymentEntity(paymentTransactionDto);
        payment.setPaymentDate(localDateNow);
        paymentDao.save(payment);

        Loan loan;
        Optional<Loan> optionalLoan = loanDao
                .findLoanByIdAndCustomerId(paymentTransactionDto.getPrincipalDebtId(), paymentTransactionDto.getCustomerId());

        // Updating principal debt
        if(optionalLoan.isPresent()){
            loan = optionalLoan.get();
            loan.setUnpaidLoanAmount(BigDecimal.ZERO);
            loan.setPayment(payment);
            loanDao.save(loan);
        }else{
            throw new RuntimeException("Loan couldn't be found! Or Customer.id & Loan.id doesn't match!");
        }



        BigDecimal interest = InterestCalculator.calculateInterest(loan.getPaymentDueDate(), localDateNow,loan.getLoanAmount());
        BigDecimal moneyBeingPaid = paymentTransactionDto.getMoneyBeingPaid();

        if(moneyBeingPaid.compareTo(interest.add(loan.getLoanAmount(),MathContext.DECIMAL32)) == 0){

        }else{
            throw new RuntimeException("Money being paid is not equal to interest plus principle loan");
        }

        Loan interestDebt = new Loan();
        // saving late payment interest
        if(optionalLoan.isPresent()){
            interestDebt.setCustomer(loan.getCustomer());
            interestDebt.setLoanCreationDate(LocalDate.now());
            interestDebt.setLoanAmount(interest);
            interestDebt.setLoanType(LoanType.LATE_PAYMENT_INTEREST);
            interestDebt.setUnpaidLoanAmount(BigDecimal.ZERO);
            interestDebt.setPayment(payment);
            interestDebt.setLoan(loan);

            loanDao.save(interestDebt);
        }
    }






}




