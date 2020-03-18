package com.deliver.bank.bank.person.dto;

import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.entities.enums.PersonProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PersonRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String identifier; //CNPJ or CPF
    private String fullName;
    private String address;
    private String email;
    private String telephoneNumber;
    private String password;

    public PersonRequestDTO(Person person) {
        this.identifier = person.getIdentifier();
        this.fullName = person.getFullName();
        this.address = person.getAddress();
        this.email = person.getEmail();
        this.telephoneNumber = person.getTelephoneNumber();
        this.password = person.getPassword();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
