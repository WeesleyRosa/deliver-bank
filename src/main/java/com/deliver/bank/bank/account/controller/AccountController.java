package com.deliver.bank.bank.account.controller;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAll() {
        List<Account> list = accountService.getAll();
        List<AccountDTO> listDto = list.stream().map(obj -> new AccountDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountDTO> findById(@PathVariable String accountNumber) {
        AccountDTO accountDto = new AccountDTO(accountService.findByAccountNumber(accountNumber));
        return ResponseEntity.ok().body(accountDto);
    }
}