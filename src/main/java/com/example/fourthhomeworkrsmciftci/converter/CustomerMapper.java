package com.example.fourthhomeworkrsmciftci.converter;


import com.example.fourthhomeworkrsmciftci.dto.CustomerDto;
import com.example.fourthhomeworkrsmciftci.dto.CustomerSavingDto;
import com.example.fourthhomeworkrsmciftci.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerSavingDto convertToCustomerDto(Customer customer);
    List<CustomerSavingDto> convertToCustomerDtoList(List<Customer> customerList);

    CustomerDto customerRetuningAllFieldsDto(Customer customer);
    List<CustomerDto> convertTocustomerRetuningAllFieldsDtoList(List<Customer> customerList);

    Customer convertToCustomer(CustomerSavingDto customerSavingDto);


}
