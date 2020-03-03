package com.deliver.bank.bank.person.dto;

import com.deliver.bank.bank.person.entities.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

    private Long personId;
    private String identifier; //CNPJ or CPF
    private String fullName;
    private String address;
    private String email;
    private String telephoneNumber;

    public PersonDTO(Person person) {
        this.personId = person.getPersonId();
        this.identifier = person.getIdentifier();
        this.fullName = person.getFullName();
        this.address = person.getAddress();
        this.email = person.getEmail();
        this.telephoneNumber = person.getTelephoneNumber();
    }

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public String getIdentifier(){
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
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail(){
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

}
