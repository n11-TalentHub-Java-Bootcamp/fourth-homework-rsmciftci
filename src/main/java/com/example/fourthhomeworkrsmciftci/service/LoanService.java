package com.example.fourthhomeworkrsmciftci.service;

import com.example.fourthhomeworkrsmciftci.converter.LoanMapper;
import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
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
}
