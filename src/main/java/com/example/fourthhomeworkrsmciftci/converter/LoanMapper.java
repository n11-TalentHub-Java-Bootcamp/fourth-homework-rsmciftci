package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSavingDto;
import com.example.fourthhomeworkrsmciftci.dto.LoanSumOfLoansOfUser;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(source = "customerId", target = "customer.id")
    Loan convertLoanSavingDtoToLoan(LoanSavingDto loanSavingDto);

    @Mapping(source = "loan.id", target ="principalDebtId")
    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "customer.id", target = "customerId")
    LoanDto convertLoanToLoanDto(Loan loan);

    List<LoanDto> convertLoanListToLoanDtoList(List<Loan> loanList);


}
