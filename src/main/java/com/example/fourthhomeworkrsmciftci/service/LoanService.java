package com.example.fourthhomeworkrsmciftci.service;

import com.example.fourthhomeworkrsmciftci.converter.LoanConverter;
import com.example.fourthhomeworkrsmciftci.converter.LoanMapper;
import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSumOfLoansOfUser;
import com.example.fourthhomeworkrsmciftci.dto.LoanTotalInterestDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.service.entityService.LoanEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanService {

    private LoanEntityService loanEntityService;


    public LoanDto save(LoanSavingDto loanSavingDto){
        LoanMapper mapper = LoanMapper.INSTANCE;
        Loan loan = mapper.convertLoanSavingDtoToLoan(loanSavingDto);

        LocalDate now = LocalDate.now();
        BigDecimal unpaidLoanAmount = loan.getLoanAmount();

        loan.setLoanCreationDate(now);
        loan.setUnpaidLoanAmount(unpaidLoanAmount);
        loan.setLoanType(LoanType.PRINCIPAL_DEBT);

        Loan savedLoan = loanEntityService.save(loan);
        LoanDto loanDto = mapper.convertLoanToLoanDto(savedLoan);
        return loanDto;
    }

    public List<LoanDto> findLoanByLoanCreationDateBetweenAndLoanTypeIs(LocalDate date1, LocalDate date2, LoanType loanType){

        LocalDate earlierDate;
        LocalDate laterDate;
        if(date1.isBefore(date2)){
            earlierDate = date1;
            laterDate = date2;
        }else{
            earlierDate = date2;
            laterDate = date1;
        }

        List<Loan> loanList = loanEntityService.findLoanByLoanCreationDateBetweenAndLoanTypeIs(earlierDate,laterDate,loanType);
        LoanMapper mapper = LoanMapper.INSTANCE;
        List<LoanDto> loanDtoList = mapper.convertLoanListToLoanDtoList(loanList);
        return loanDtoList;
    }

    public List<LoanDto> findPrincipleLoansOfUser(Long id, LoanType loanType){
        BigDecimal unpaidLoanAmountGreaterThan = BigDecimal.ZERO;
        LoanMapper mapper = LoanMapper.INSTANCE;

        List<Loan> principleLoanListOfUser = loanEntityService.findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIs(id,unpaidLoanAmountGreaterThan,loanType);
        List<LoanDto> principleLoanDtoListOfUser = mapper.convertLoanListToLoanDtoList(principleLoanListOfUser);
        return principleLoanDtoListOfUser;
    }

    public List<LoanDto> findOverduePrincipleLoansOfUser(Long id, LoanType loanType){
        BigDecimal unpaidLoanAmountGreaterThan = BigDecimal.ZERO;
        LocalDate localDateNow = LocalDate.now();
        LoanMapper mapper = LoanMapper.INSTANCE;

        List<Loan> overduePrincipleLoanListOfUser = loanEntityService.findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndPaymentDueDateBeforeAndLoanTypeIs(id,unpaidLoanAmountGreaterThan,localDateNow,loanType);
        List<LoanDto> overduePrincipleLoanDtoListOfUser = mapper.convertLoanListToLoanDtoList(overduePrincipleLoanListOfUser);
        return overduePrincipleLoanDtoListOfUser;
    }

    public LoanSumOfLoansOfUser calculateSumOfUnpaidLoansOfUser(Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;
        BigDecimal loanAmountGreaterThan = BigDecimal.ZERO;

        List<Loan> loanListOfUser = loanEntityService.calculateSumOfUnpaidLoansOfUser(id,loanAmountGreaterThan,loanType);

        LoanConverter loanConverter = new LoanConverter();

       LoanSumOfLoansOfUser sumOfLoansOfUser = loanConverter.converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(loanListOfUser);


        return sumOfLoansOfUser;
    }

    public LoanSumOfLoansOfUser calculateSumOfOverdueUnpaidLoansOfUser(Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;
        BigDecimal loanAmountGreaterThan = BigDecimal.ZERO;
        LocalDate now = LocalDate.now();

        List<Loan> overdueLoanListOfUser = loanEntityService.calculateSumOfOverdueUnpaidLoansOfUser(id,loanAmountGreaterThan,loanType,now);

        LoanConverter loanConverter = new LoanConverter();

        LoanSumOfLoansOfUser sumOfLoansOfUser = loanConverter.converLoanListOfUserToLoanLoanSumOfLoansOfUserDto(overdueLoanListOfUser);

        return sumOfLoansOfUser;
    }

    public LoanTotalInterestDto calculateTotalInterestOfUser(Long id){
        LoanType loanType = LoanType.PRINCIPAL_DEBT;
        BigDecimal loanAmountGreaterThan = BigDecimal.ZERO;
        LocalDate now = LocalDate.now();

        List<Loan> overdueLoanListOfUser = loanEntityService.calculateSumOfOverdueUnpaidLoansOfUser(id,loanAmountGreaterThan,loanType,now);

        LoanConverter loanConverter = new LoanConverter();

        LoanTotalInterestDto totalInterest = loanConverter.converLoanListOfUserToLoanTotalInterestDto(overdueLoanListOfUser);

        return totalInterest;
    }




}
