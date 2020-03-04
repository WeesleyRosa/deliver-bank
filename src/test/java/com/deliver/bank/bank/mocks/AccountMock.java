package com.deliver.bank.bank.mocks;

import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.entities.AccountType;

import java.math.BigDecimal;

public class AccountMock {

    public static Account returnSingleAccount() {
        return Account.builder()
                .id(Long.valueOf(1))
                .accountNumber("1000")
                .balance(BigDecimal.valueOf(2000.50))
                .agency("2001 Assis Brasil")
                .password("210299")
                .accountType(AccountType.CHECKING_ACCOUNT)
                .build();
    }
}
