package net.ibank.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ibank.springboot.model.Account;
import net.ibank.springboot.model.AccountBalance;
import net.ibank.springboot.model.Product;
import net.ibank.springboot.model.Transaction;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
	
	Optional<AccountBalance> findByAccountId(Long accountId);

}
