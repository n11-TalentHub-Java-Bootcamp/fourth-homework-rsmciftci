package com.example.fourthhomeworkrsmciftci.controller;

import com.example.fourthhomeworkrsmciftci.dto.CustomerDto;
import com.example.fourthhomeworkrsmciftci.dto.CustomerSavingDto;
import com.example.fourthhomeworkrsmciftci.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Saving a customer")
    @ApiResponses(
            @ApiResponse(responseCode = "500",description="if turkishIdentityNumber is not 11 characters," +
                    "or email/turkishIdentity number is included in db")
    )
    @PostMapping("")
    public ResponseEntity save(@RequestBody CustomerSavingDto customerSavingDto){
        CustomerSavingDto savedCustomerSavingDto = customerService.save(customerSavingDto);
        return ResponseEntity.ok(savedCustomerSavingDto);
    }



    // (1b)
    @Operation(summary = "Delete Customer by its id")
    @ApiResponses(
            @ApiResponse(responseCode = "500",description="if customer doesn't exist")
            )
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        customerService.delete(id);
    }

    // (1d)
    @Operation(summary = "Finds All Customers here")
    @GetMapping("")
    public ResponseEntity findAll(){
        List<CustomerDto> customerDtos = customerService.findAll();
        return ResponseEntity.ok(customerDtos);
    }



}
