package com.deliver.bank.bank.mocks;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.AccountType;

import java.math.BigDecimal;

public class AccountDTOMock {

    public static AccountDTO returnSingleAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(Long.valueOf(1));
        accountDTO.setAccountNumber("1000");
        accountDTO.setBalance(BigDecimal.valueOf(2000.50));
        accountDTO.setAgency("2001 Assis Brasil");
        accountDTO.setPassword("210299");
        accountDTO.setAccountType(AccountType.CHECKING_ACCOUNT);
        return accountDTO;
    }
}
