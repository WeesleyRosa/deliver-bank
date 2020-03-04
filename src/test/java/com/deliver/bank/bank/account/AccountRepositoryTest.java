package com.deliver.bank.bank.account;

import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.repository.AccountRepository;
import com.deliver.bank.bank.mocks.AccountMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void beforeEach() {
        accountRepository.deleteAll();
    }

    @Deprecated
    @Test
    @DisplayName("Create Account Test")
    void givenAccountMock_ThenCreateAccount() {
        Account account = AccountMock.returnSingleAccount();
        accountRepository.save(account);
        int countAccount = accountRepository.findAll().size();
        assertThat(countAccount).isEqualTo(1);
    }

    @Test
    @DisplayName("Find Account By AccountNumber")
    void givenAccountNumber_WhenGetByAccountNumber_ThenReturnAccountObject() {
        Account account = AccountMock.returnSingleAccount();
        accountRepository.save(account);
        Object objectAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        assertThat(objectAccount).isEqualTo(account);
    }
}
