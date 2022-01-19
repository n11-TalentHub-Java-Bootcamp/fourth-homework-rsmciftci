package com.example.fourthhomeworkrsmciftci.converter;

import com.example.fourthhomeworkrsmciftci.dto.PaymentTransactionDto;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    //TODO:delete if not needed
    /*
    @Mapping(source="principalDebtId",target = "loan.id")
    @Mapping(source="customerId",target = "customer.id")
    Payment convertPaymentTransactionDtoToPaymentEntity(PaymentTransactionDto paymentTransactionDto);
*/
}
