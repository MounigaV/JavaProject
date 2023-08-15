package net.ibank.springboot.service;

import java.util.List;

import net.ibank.springboot.model.AccountBalance;
import net.ibank.springboot.model.Transaction;

public interface TransactionService {
	

	List<Transaction> getAllTransaction();

	List<Transaction> getTransactionByAccountId(long accountId);


	AccountBalance createDepositTransaction(Transaction transaction);


	AccountBalance createWithdrawlTransaction(Transaction transaction);


}
