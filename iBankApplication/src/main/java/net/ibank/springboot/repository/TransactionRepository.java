package net.ibank.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ibank.springboot.model.Account;
import net.ibank.springboot.model.Product;
import net.ibank.springboot.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByAccountId(Long accountId);

}
