package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.CustomerDto;
import com.example.fourthhomeworkrsmciftci.dto.CustomerSavingDto;
import com.example.fourthhomeworkrsmciftci.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    // (1a)
    @PostMapping("")
    public ResponseEntity save(@RequestBody CustomerSavingDto customerSavingDto){
        CustomerSavingDto savedCustomerSavingDto = customerService.save(customerSavingDto);
        return ResponseEntity.ok(savedCustomerSavingDto);
    }



    // (1b)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        customerService.delete(id);
    }

    // (1d)
    @GetMapping("")
    public ResponseEntity findAll(){
        List<CustomerDto> customerDtos = customerService.findAll();
        return ResponseEntity.ok(customerDtos);
    }



}
