package com.deliver.bank.bank.person.entities;

import com.deliver.bank.bank.person.entities.enums.PersonProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long personId;

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

    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profiles = new HashSet<>();

    public Person() {
        this.addProfile(PersonProfile.USER);
    }

    public Person (Long personId,
                   String identifier,
                   String fullName, String address,
                   String email,
                   String telephoneNumber,
                   String password) {
        this();
        this.personId = personId;
        this.identifier = identifier;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PersonProfile> getProfiles() {
        return this.profiles.stream().map(PersonProfile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(PersonProfile profile) {
        this.profiles.add(profile.getProfileCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPersonId().equals(person.getPersonId()) &&
                getIdentifier().equals(person.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getIdentifier());
    }
}
