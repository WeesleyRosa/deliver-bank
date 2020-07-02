package com.deliver.bank.bank.domain.user.api.v1.controller.response;

import com.deliver.bank.bank.domain.user.api.v1.controller.request.AddressDTO;
import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserGetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String identifier;
    private String fullName;
    private AddressDTO addressDTO;
    private LocalDate birthday;
    private UserType userType;
    private String email;
    private String telephone;
    private UserStatus userStatus;
    private UserProfile userProfile;

    public UserGetDTO(User user) {
        this.id = user.getId();
        this.identifier = user.getIdentifier();
        this.fullName = user.getFullName();
        this.addressDTO = new AddressDTO(user.getAddress());
        this.birthday = user.getBirthday();
        this.userType = user.getUserType();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.userStatus = user.getUserStatus();
        this.userProfile = user.getUserProfile();
    }
}
