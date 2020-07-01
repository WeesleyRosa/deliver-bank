package com.deliver.bank.bank.user.entities;

import com.deliver.bank.bank.user.entities.enums.UserProfile;
import com.deliver.bank.bank.user.entities.enums.UserStatus;
import com.deliver.bank.bank.user.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String identifier;

    private String fullName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private LocalDate birthday;
    private UserType userType;
    private String email;
    private String telephone;
    private String password;
    private UserStatus userStatus;
    private UserProfile userProfile;
    private LocalDateTime createdAt;
}
