package com.deliver.bank.bank.person.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

    private Long id;
    private String identifier; //CNPJ or CPF
    private String fullName;
    private String address;
    private String email;
    private String telephoneNumber;
}
