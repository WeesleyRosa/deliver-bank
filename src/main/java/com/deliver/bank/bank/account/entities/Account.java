package com.deliver.bank.bank.account.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column (name = "agency")
    private String agency;
    @Column (name = "password", nullable = false)
    private String password;
    @Column (name = "accountType")
    private AccountType accountType;

    public Account(Long id, String accountNumber, BigDecimal balance, String agency, String password, AccountType accountType) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.agency = agency;
        this.password = password;
        this.accountType = accountType;
    }
}
