package com.deliver.bank.bank.account;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.repository.AccountRepository;
import com.deliver.bank.bank.account.service.AccountService;
import com.deliver.bank.bank.mocks.AccountDTOMock;
import com.deliver.bank.bank.mocks.AccountMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {


    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void beforeEach() {
        accountService = Mockito.spy(new AccountService(accountRepository));
    }



    @Test
    @DisplayName("Return Account when given AccountDTO")
    void givenAccountDTO_thenReturnAccount() {
        AccountDTO accountDTO = AccountDTOMock.returnSingleAccount();
        Account account = AccountMock.returnSingleAccount();
        when(accountService.fromDTO(accountDTO)).thenReturn(account);
    }
}
