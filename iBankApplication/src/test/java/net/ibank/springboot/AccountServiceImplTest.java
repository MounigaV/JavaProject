package net.ibank.springboot;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import net.ibank.springboot.enums.AccountStatus;
import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.Account;
import net.ibank.springboot.repository.AccountRepository;
import net.ibank.springboot.service.AccountServiceImpl;

@SpringBootTest
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        // Set account properties

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        // Perform assertions based on your business logic
    }

    @Test
    public void testUpdateAccount() {
        long accountId = 1L;
        Account existingAccount = new Account();
        // Set existingAccount properties

        Account updatedAccount = new Account();
        // Set updatedAccount properties

        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(updatedAccount);

        Account result = accountService.updateAccount(updatedAccount, accountId);

        assertNotNull(result);
        // Perform assertions based on your business logic
    }

    @Test
    public void testUpdateAccountNotFound() {
        long accountId = 1L;
        Account updatedAccount = new Account();
        // Set updatedAccount properties

        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.updateAccount(updatedAccount, accountId));
    }

    @Test
    public void testGetAccountById() {
        long accountId = 1L;
        Account account = new Account();
        // Set account properties

        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(accountId);

        assertNotNull(result);
        // Perform assertions based on your business logic
    }

    @Test
    public void testGetAccountByIdNotFound() {
        long accountId = 1L;

        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountById(accountId));
    }

    @Test
    public void testDeleteAccount() {
        long accountId = 1L;
        Account account = new Account();
        // Set account properties

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        assertDoesNotThrow(() -> accountService.deleteAccount(accountId));
        // You can also assert that accountRepository.delete(account) was called
    }

    @Test
    public void testDeleteAccountNotFound() {
        long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.deleteAccount(accountId));
    }
}
