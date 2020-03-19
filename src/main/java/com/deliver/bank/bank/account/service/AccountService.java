package com.deliver.bank.bank.account.service;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void save(AccountDTO accountDTO){
        accountRepository.save(fromDTO(accountDTO));
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account fromDTO(AccountDTO accountDTO){
        return new Account(accountDTO.getId(), accountDTO.getAccountNumber(), accountDTO.getBalance(),
                accountDTO.getAgency(), accountDTO.getPassword(),accountDTO.getAccountType() );
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
