package net.ibank.springboot.service;

import java.util.List;

import net.ibank.springboot.model.Account;

public interface AccountService {
	Account createAccount(Account account);

	Account updateAccount(Account account, long id);


	Account getAccountById(long accountId);

	void deleteAccount(long id);
}
