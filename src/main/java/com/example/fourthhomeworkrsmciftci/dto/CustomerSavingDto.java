package com.example.fourthhomeworkrsmciftci.dto;

import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.entity.Payment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class CustomerSavingDto {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String turkishIdentityNumber;


}
