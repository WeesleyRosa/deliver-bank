package com.deliver.bank.bank.domain.user.api.v1.controller.request;

import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
