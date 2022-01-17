package com.example.fourthhomeworkrsmciftci.service;

import com.example.fourthhomeworkrsmciftci.converter.CustomerMapper;
import com.example.fourthhomeworkrsmciftci.dto.CustomerDto;
import com.example.fourthhomeworkrsmciftci.dto.CustomerSavingDto;
import com.example.fourthhomeworkrsmciftci.entity.Customer;
import com.example.fourthhomeworkrsmciftci.service.entityService.CustomerEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService{

    private CustomerEntityService customerEntityService;


    public CustomerSavingDto save(CustomerSavingDto customerSavingDto){
        CustomerMapper mapper = CustomerMapper.INSTANCE;
        Customer customer = mapper.convertToCustomer(customerSavingDto);
        customer = customerEntityService.save(customer);
        CustomerSavingDto savedCustomerSavingDto = mapper.convertToCustomerDto(customer);
        return savedCustomerSavingDto;

    }


    public void delete(Long id) {

        Customer customer = findCustomerById(id);

        customerEntityService.delete(customer);
    }

    public List<CustomerDto> findAll(){
        List<Customer> customerList = customerEntityService.findAll();
        List<CustomerDto> customerDtoList = CustomerMapper.INSTANCE.convertTocustomerRetuningAllFieldsDtoList(customerList);
        return customerDtoList;
    }
    private Customer findCustomerById(Long id) {
        Customer customer;
        Optional<Customer> optionalCustomer = customerEntityService.findById(id);
        if (optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        } else {
            throw new RuntimeException("Customer not found!");
        }
        return customer;
    }

}
