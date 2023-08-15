package net.ibank.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ibank.springboot.model.Beneficiary;
import net.ibank.springboot.model.Product;
import net.ibank.springboot.model.Transaction;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	
	Optional<Beneficiary> findByAccountIdAndBeneAccountId(Long accountId,Long beneAccountId);
	
	List<Beneficiary> findByAccountId(Long accountId);

}
