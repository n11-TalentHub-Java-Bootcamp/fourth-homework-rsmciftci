package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.LoanDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentDto;
import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target="loanId", source = "loan.id")
    @Mapping(target="customerId", source = "customer.id")
    PaymentDto convertPaymentToPaymentDto(Payment payment);
    List<PaymentDto> convertPaymentListToPaymentDtoList(List<Payment> paymentList);


}
