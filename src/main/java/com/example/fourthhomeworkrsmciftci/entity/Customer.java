package com.example.fourthhomeworkrsmciftci.entity;

import com.example.fourthhomeworkrsmciftci.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name ="CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Column(unique=true)
    private String email;

    private String phoneNumber;

    @Column(unique=true)
    @Size(min = 11, max = 11)
    private String turkishIdentityNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Loan> loans = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Payment> payments = new java.util.LinkedHashSet<>();


}
