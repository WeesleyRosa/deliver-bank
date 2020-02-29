package com.deliver.bank.bank.account.service;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public void accountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void save(AccountDTO accountDTO){
        accountRepository.save(fromDTO(accountDTO));
    }
    public Account fromDTO(AccountDTO accountDTO){
        return new Account(accountDTO.getId(), accountDTO.getAccountNumber(), accountDTO.getBalance(), accountDTO.getAgency(), accountDTO.getPassword(), accountDTO.getAccountType());
    }
}
