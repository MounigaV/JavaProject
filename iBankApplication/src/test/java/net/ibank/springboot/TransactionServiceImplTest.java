package net.ibank.springboot;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import net.ibank.springboot.enums.TransactionType;
import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.AccountBalance;
import net.ibank.springboot.model.Transaction;
import net.ibank.springboot.repository.AccountBalanceRepository;
import net.ibank.springboot.repository.TransactionRepository;
import net.ibank.springboot.service.TransactionServiceImpl;

@SpringBootTest
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountBalanceRepository accountBalanceRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepositTransaction() {
        Transaction transaction = new Transaction();
        // Set transaction properties

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAccountId(transaction.getAccountId());
        accountBalance.setBalance(1000.0); // Initial balance

        when(accountBalanceRepository.findByAccountId(anyLong())).thenReturn(Optional.of(accountBalance));
        when(accountBalanceRepository.save(any(AccountBalance.class))).thenReturn(accountBalance);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        AccountBalance result = transactionService.createDepositTransaction(transaction);

        assertNotNull(result);
        assertEquals(accountBalance.getBalance() + transaction.getAmount(), result.getBalance(), 0.001);
    }

    @Test
    public void testCreateWithdrawlTransactionSufficientBalance() {
        Transaction transaction = new Transaction();
        // Set transaction properties

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAccountId(transaction.getAccountId());
        accountBalance.setBalance(1000.0); // Initial balance

        when(accountBalanceRepository.findByAccountId(anyLong())).thenReturn(Optional.of(accountBalance));
        when(accountBalanceRepository.save(any(AccountBalance.class))).thenReturn(accountBalance);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        AccountBalance result = transactionService.createWithdrawlTransaction(transaction);

        assertNotNull(result);
        assertEquals(accountBalance.getBalance() - transaction.getAmount(), result.getBalance(), 0.001);
    }

    @Test
    public void testCreateWithdrawlTransactionInsufficientBalance() {
        Transaction transaction = new Transaction();
        transaction.setAmount(200);
        // Set transaction properties

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAccountId(transaction.getAccountId());
        accountBalance.setBalance(100.0); // Initial balance

        when(accountBalanceRepository.findByAccountId(anyLong())).thenReturn(Optional.of(accountBalance));

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createWithdrawlTransaction(transaction));
    }

  

}
