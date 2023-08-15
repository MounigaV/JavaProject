package net.ibank.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ibank.springboot.enums.AccountStatus;
import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.Account;
import net.ibank.springboot.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account updateAccount(Account account, long accountId) {
		Optional<Account> accountDb = this.accountRepository.findByAccountId(accountId);
		
		if(accountDb.isPresent()) {
			Account accountUpdate = accountDb.get();
			accountUpdate.setAccountName(account.getAccountName());
			accountUpdate.setEmail(account.getEmail());
			accountUpdate.setPhone(account.getPhone());
			accountUpdate.setStatus(account.getStatus());
			accountRepository.save(accountUpdate);
			return accountUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + account.getAccountId());
		}		
	}



	@Override
	public Account getAccountById(long accountId) {
		
		Optional<Account> accountDb = this.accountRepository.findByAccountId(accountId);
		
		if(accountDb.isPresent()) {
			return accountDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + accountId);
		}
	}

	@Override
	public void deleteAccount(long accountId) {
		Optional<Account> accountDb = this.accountRepository.findById(accountId);
		
		if(accountDb.isPresent()) {
			this.accountRepository.delete(accountDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + accountId);
		}
		
	}

}
