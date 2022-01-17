package com.example.fourthhomeworkrsmciftci.entity;

import com.example.fourthhomeworkrsmciftci.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "PAYMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<Loan> loan;

    @ManyToOne
    @JoinColumn(name = "customer_id"/*, foreignKey = @ForeignKey(name ="FK_PAYMENT_CUSTOMER_ID")*/)
    private Customer customer;


}