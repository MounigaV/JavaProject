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

import net.ibank.springboot.model.Account;
import net.ibank.springboot.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable long id){
		return ResponseEntity.ok().body(accountService.getAccountById(id));
	}
	
	@PostMapping("/account")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		return ResponseEntity.ok().body(this.accountService.createAccount(account));
	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody Account account){
		
		return ResponseEntity.ok().body(this.accountService.updateAccount(account,id));
	}

	@DeleteMapping("/account/{id}")
	public HttpStatus deleteAccount(@PathVariable long id){
		this.accountService.deleteAccount(id);
		return HttpStatus.OK;
	}
}
