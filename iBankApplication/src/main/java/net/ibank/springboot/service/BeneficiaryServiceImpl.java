package net.ibank.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.Account;
import net.ibank.springboot.model.Beneficiary;
import net.ibank.springboot.model.Product;
import net.ibank.springboot.repository.AccountRepository;
import net.ibank.springboot.repository.BeneficiaryRepository;


@Service
@Transactional
public class BeneficiaryServiceImpl implements BeneficiaryService{

	
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Override
	public Beneficiary createBeneficiary(Beneficiary beneficiary) {
		validateBeneficiary(beneficiary);
		return beneficiaryRepository.save(beneficiary);
	}

	private void validateBeneficiary(Beneficiary beneficiary) {
		Long accountId = beneficiary.getAccountId();
		Optional<Account> accountDb = this.accountRepository.findByAccountId(accountId);

		if(!accountDb.isPresent()) {
			throw new ResourceNotFoundException("Account not found with id : " + accountId);
		}
		
		Long beneAccountId = beneficiary.getBeneAccountId();
		Optional<Account> beneAccountDb = this.accountRepository.findByAccountId(beneAccountId);

		if(!beneAccountDb.isPresent()) {
			throw new ResourceNotFoundException("Beneficiary not found with id : " + beneAccountId);
		}
	}

	@Override
	public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
		validateBeneficiary(beneficiary);
		Optional<Beneficiary> beneficiaryDb = this.beneficiaryRepository.findByAccountIdAndBeneAccountId(beneficiary.getAccountId(),beneficiary.getBeneAccountId());
		
		if(beneficiaryDb.isPresent()) {
			Beneficiary beneficiaryUpdate = beneficiaryDb.get();
			beneficiaryUpdate.setBeneIfsccode(beneficiary.getBeneIfsccode());
			beneficiary.setBeneName(beneficiary.getBeneName());
			beneficiary.setStatus(beneficiary.getStatus());
			beneficiaryRepository.save(beneficiary);
			return beneficiaryUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + beneficiary.getId());
		}		
	}


	@Override
	public List<Beneficiary> getBeneficiaryById(long accountId) {
		
		
		List<Beneficiary> beneficiaryDb = this.beneficiaryRepository.findByAccountId(accountId);
		
		if(beneficiaryDb != null || !beneficiaryDb.isEmpty()) {
			return beneficiaryDb;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + accountId);
		}
	}

	@Override
	public void deleteBeneficiary(long accountId,long beneAccountId) {
		
		Optional<Beneficiary> beneficiaryDb = this.beneficiaryRepository.findByAccountIdAndBeneAccountId(accountId,beneAccountId);
		
		if(beneficiaryDb.isPresent()) {
			this.beneficiaryRepository.delete(beneficiaryDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " +  beneAccountId);
		}
		
	}

}
