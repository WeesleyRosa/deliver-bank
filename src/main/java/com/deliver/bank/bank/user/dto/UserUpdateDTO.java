package com.deliver.bank.bank.user.dto;

import com.deliver.bank.bank.user.entities.User;
import com.deliver.bank.bank.user.entities.Address;
import com.deliver.bank.bank.user.entities.enums.UserProfile;
import com.deliver.bank.bank.user.entities.enums.UserStatus;
import com.deliver.bank.bank.user.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private static final long serialVersionUID = 1L;

    private String identifier;
    private String fullName;
    private AddressDTO addressDTO;
    private LocalDate birthday;
    private UserType userType;
    private String email;
    private String telephone;
    private UserStatus userStatus;
    private UserProfile userProfile;
}
