package net.ibank.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ibank.springboot.model.Account;
import net.ibank.springboot.model.Product;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByAccountId(Long accountId);

}
