package com.deliver.bank.bank.account.controller;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createNewAccount(@RequestBody AccountDTO accountDTO){
        accountService.save(accountDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
