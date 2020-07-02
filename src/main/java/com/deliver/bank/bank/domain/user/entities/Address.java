package com.deliver.bank.bank.domain.user.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private User user;

    private String cep;
    private String city;
    private String country;
    private String houseNumber;
    private String streetName;

    public Address(String cep, String city, String country, String houseNumber, String streetName) {
        this.cep = cep;
        this.city = city;
        this.country = country;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
    }
}
