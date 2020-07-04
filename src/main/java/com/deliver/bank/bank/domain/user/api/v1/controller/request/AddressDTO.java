package com.deliver.bank.bank.domain.user.api.v1.controller.request;

import com.deliver.bank.bank.domain.user.entities.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cep;
    private String city;
    private String country;
    private String houseNumber;
    private String streetName;

    public AddressDTO(Address address) {
        this.cep = address.getCep();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.houseNumber = address.getHouseNumber();
        this.streetName = address.getStreetName();
    }
}
