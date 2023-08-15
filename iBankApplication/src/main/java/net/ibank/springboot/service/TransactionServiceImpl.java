package net.ibank.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ibank.springboot.enums.TransactionType;
import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.AccountBalance;
import net.ibank.springboot.model.Transaction;
import net.ibank.springboot.repository.AccountBalanceRepository;
import net.ibank.springboot.repository.TransactionRepository;
import net.ibank.springboot.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountBalanceRepository accountBalanceRepository;
	
	
	@Override
	public AccountBalance createDepositTransaction(Transaction transaction) {
		transaction.setType(TransactionType.CREDIT);
		long accountId = transaction.getAccountId();
		Optional<AccountBalance> accountBalanceDB = this.accountBalanceRepository.findByAccountId(accountId);
		AccountBalance accBalance ;
		if(accountBalanceDB.isPresent()) {
			accBalance = accountBalanceDB.get();
			accBalance.setBalance(accBalance.getBalance()+transaction.getAmount());
			
		}
		else {
			accBalance = new AccountBalance();
			accBalance.setAccountId(accountId);
			accBalance.setBalance(transaction.getAmount());
		}
		transactionRepository.save(transaction);
		return accountBalanceRepository.save(accBalance);
		
	}

	@Override
	public AccountBalance createWithdrawlTransaction(Transaction transaction) {
		transaction.setType(TransactionType.DEBIT);
		long accountId = transaction.getAccountId();
		Optional<AccountBalance> accountBalanceDB = this.accountBalanceRepository.findByAccountId(accountId);
		if(accountBalanceDB.isPresent()) {
			AccountBalance accBalance = accountBalanceDB.get();
			if(accBalance.getBalance() > transaction.getAmount()) {
				accBalance.setBalance(accBalance.getBalance()-transaction.getAmount());
				transactionRepository.save(transaction);
				return accountBalanceRepository.save(accBalance);
				
			}
			else {
				throw new ResourceNotFoundException("Account balance nt sufficient for withdrawl");
			}
			
		}
		else {
			throw new ResourceNotFoundException("Account balance nt found for withdrawl");
		}
		
	}
	

	@Override
	public List<Transaction> getAllTransaction() {
		return this.transactionRepository.findAll();
	}

	@Override
	public List<Transaction> getTransactionByAccountId(long accountId) {
		
		List<Transaction> transactionDb = this.transactionRepository.findByAccountId(accountId);
		
		return transactionDb;
	}


	

}
