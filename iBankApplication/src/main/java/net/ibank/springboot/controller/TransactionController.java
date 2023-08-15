package net.ibank.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.ibank.springboot.model.AccountBalance;
import net.ibank.springboot.model.Transaction;
import net.ibank.springboot.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransaction(){
		return ResponseEntity.ok().body(transactionService.getAllTransaction());
	}
	
	@GetMapping("/transactions/{id}")
	public ResponseEntity<List<Transaction>> getTransactionById(@PathVariable long id){
		return ResponseEntity.ok().body(transactionService.getTransactionByAccountId(id));
	}
	
	@PostMapping("/deposit_transaction")
	public ResponseEntity<AccountBalance> createDepositTransaction(@RequestBody Transaction transaction){
		return ResponseEntity.ok().body(this.transactionService.createDepositTransaction(transaction));
	}
	
	@PostMapping("/withdrawl_transaction")
	public ResponseEntity<AccountBalance> createWithdrawlTransaction(@RequestBody Transaction transaction){
		return ResponseEntity.ok().body(this.transactionService.createWithdrawlTransaction(transaction));
	}


}
