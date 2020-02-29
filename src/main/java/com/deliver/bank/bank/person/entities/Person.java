package com.deliver.bank.bank.person.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "identifier", length = 14, nullable = false)
    private String identifier; //CNPJ or CPF
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "email" )
    private String email;
    @Column(name = "telephoneNumber")
    private String telephoneNumber;
}
