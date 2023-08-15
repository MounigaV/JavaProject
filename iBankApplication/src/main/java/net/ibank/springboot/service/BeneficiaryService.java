package net.ibank.springboot.service;

import java.util.List;

import net.ibank.springboot.model.Beneficiary;
import net.ibank.springboot.model.Product;

public interface BeneficiaryService {
	Beneficiary createBeneficiary(Beneficiary product);

	Beneficiary updateBeneficiary(Beneficiary product);


	List<Beneficiary> getBeneficiaryById(long beneficiaryId);

	void deleteBeneficiary(long accountId,long beneAccountId);
}
