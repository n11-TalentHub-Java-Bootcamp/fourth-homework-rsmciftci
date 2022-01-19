package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSumOfLoansOfUser;
import com.example.fourthhomeworkrsmciftci.dto.LoanTotalInterestDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.interestCalculator.InterestCalculator;
import com.example.fourthhomeworkrsmciftci.service.entityService.LoanEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class LoanConverter {

    public LoanConverter() {
    }

    public static LoanSumOfLoansOfUser converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(List<Loan> listOfUnpaidLoansOfUser){

        BigDecimal sumOfPrincipleLoan = BigDecimal.ZERO;
        BigDecimal sumOfLatePaymentInterest = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;


        for(Loan loan : listOfUnpaidLoansOfUser){
            sumOfPrincipleLoan = sumOfPrincipleLoan.add(loan.getUnpaidLoanAmount());
            BigDecimal interest = InterestCalculator.calculateInterest(loan.getPaymentDueDate(),LocalDate.now(),loan.getLoanAmount());
            sumOfLatePaymentInterest = sumOfLatePaymentInterest.add(interest);
        }
        total = sumOfPrincipleLoan.add(sumOfLatePaymentInterest);


        LoanSumOfLoansOfUser sumOfLoansOfUser = new LoanSumOfLoansOfUser();
        sumOfLoansOfUser.setSumOfPrincipleLoan(sumOfPrincipleLoan);
        sumOfLoansOfUser.setSumOfLatePaymentInterest(sumOfLatePaymentInterest);
        sumOfLoansOfUser.setTotal(total);

        return  sumOfLoansOfUser;
    }


    public static LoanTotalInterestDto converLoanListOfUserToLoanTotalInterestDto(List<Loan> listOfUnpaidLoansOfUser){

        BigDecimal sumOfLatePaymentInterest = BigDecimal.ZERO;



        for(Loan loan : listOfUnpaidLoansOfUser){
            BigDecimal interest = InterestCalculator.calculateInterest(loan.getPaymentDueDate(),LocalDate.now(),loan.getLoanAmount());

            sumOfLatePaymentInterest = sumOfLatePaymentInterest.add(interest);
        }

        LoanTotalInterestDto totalInterestDto = new LoanTotalInterestDto();

        totalInterestDto.setTotalIntrest(sumOfLatePaymentInterest);


        return  totalInterestDto;
    }

    public static List<LoanDto> addInterestToList(List<LoanDto> loanDtoList){

        for(LoanDto loanDto : loanDtoList){
            loanDto = addInterestToLoanDto(loanDto);
        }
        return loanDtoList;
    }

    public static LoanDto addInterestToLoanDto(LoanDto loanDto){

        BigDecimal principalDebt = loanDto.getLoanAmount();
        LocalDate dueDate = loanDto.getPaymentDueDate();
        LocalDate now = LocalDate.now();
        boolean isLoanPaid = false;
        // calculate the interest if loan is not paid
        if(BigDecimal.ZERO.compareTo(loanDto.getUnpaidLoanAmount()) < 0){
            BigDecimal interest = InterestCalculator.calculateInterest(dueDate,now,principalDebt);
            loanDto.setInterest(interest);
        }

        return loanDto;
    }




}
