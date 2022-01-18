package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.LoanSumOfLoansOfUser;
import com.example.fourthhomeworkrsmciftci.dto.LoanTotalInterestDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.service.entityService.LoanEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public class LoanConverter {

    public LoanConverter() {
    }

    public LoanSumOfLoansOfUser converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(List<Loan> listOfUnpaidLoansOfUser){
        BigDecimal sumOfPrincipleLoan = BigDecimal.ZERO;
        BigDecimal sumOfLatePaymentInterest = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;


        for(Loan loan : listOfUnpaidLoansOfUser){
            sumOfPrincipleLoan = sumOfPrincipleLoan.add(loan.getUnpaidLoanAmount());
            sumOfLatePaymentInterest = sumOfLatePaymentInterest.add(BigDecimal.ZERO); // TODO: interestRateCalculator
        }
        total = sumOfPrincipleLoan.add(sumOfLatePaymentInterest);


        LoanSumOfLoansOfUser sumOfLoansOfUser = new LoanSumOfLoansOfUser();
        sumOfLoansOfUser.setSumOfPrincipleLoan(sumOfPrincipleLoan);
        sumOfLoansOfUser.setSumOfLatePaymentInterest(sumOfLatePaymentInterest);
        sumOfLoansOfUser.setTotal(total);

        return  sumOfLoansOfUser;
    }


    public LoanTotalInterestDto converLoanListOfUserToLoanTotalInterestDto(List<Loan> listOfUnpaidLoansOfUser){

        BigDecimal sumOfLatePaymentInterest = BigDecimal.ZERO;



        for(Loan loan : listOfUnpaidLoansOfUser){
            //sumOfPrincipleLoan = sumOfPrincipleLoan.add(loan.getUnpaidLoanAmount());
            sumOfLatePaymentInterest = sumOfLatePaymentInterest.add(BigDecimal.ZERO); // TODO: interestRateCalculator
        }

        LoanTotalInterestDto totalInterestDto = new LoanTotalInterestDto();

        totalInterestDto.setTotalIntrest(sumOfLatePaymentInterest);


        return  totalInterestDto;
    }
}
