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
public class PersonResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long personId;
    private String identifier; //CNPJ or CPF
    private String fullName;
    private String address;
    private String email;
    private String telephoneNumber;
    private Set<PersonProfile> profiles = new HashSet<>();

    public PersonResponseDTO(Person person) {
        this.personId = person.getPersonId();
        this.identifier = person.getIdentifier();
        this.fullName = person.getFullName();
        this.address = person.getAddress();
        this.email = person.getEmail();
        this.telephoneNumber = person.getTelephoneNumber();
        this.profiles = person.getProfiles();
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public Set<PersonProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<PersonProfile> profiles) {
        this.profiles = profiles;
    }
}
