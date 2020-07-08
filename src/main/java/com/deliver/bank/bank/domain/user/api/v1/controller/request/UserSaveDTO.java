package com.deliver.bank.bank.domain.user.api.v1.controller.request;

import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String identifier;
    private String fullName;
    private AddressDTO addressDTO;
    private LocalDate birthday;
    private UserType userType;
    private String email;
    private String telephone;
    private String password;
    private LocalDateTime createdAt;

    public UserSaveDTO() {
        this.createdAt = LocalDateTime.now();
    }

    public UserSaveDTO(
            String identifier,
            String fullName,
            AddressDTO addressDTO,
            LocalDate birthday,
            UserType userType,
            String email,
            String telephone,
            String password
    ) {
        this();
        this.identifier = identifier;
        this.fullName = fullName;
        this.addressDTO = addressDTO;
        this.birthday = birthday;
        this.userType = userType;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }
}
