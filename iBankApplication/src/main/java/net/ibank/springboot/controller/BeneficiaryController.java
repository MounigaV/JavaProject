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

import net.ibank.springboot.model.Beneficiary;
import net.ibank.springboot.model.Product;
import net.ibank.springboot.service.BeneficiaryService;

@RestController
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	
	
	@GetMapping("/beneficiary/{id}")
	public ResponseEntity<List<Beneficiary>> getBeneficiaryByAccountId(@PathVariable long id){
		return ResponseEntity.ok().body(beneficiaryService.getBeneficiaryById(id));
	}
	
	@PostMapping("/beneficiary")
	public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody Beneficiary beneficiary){
		return ResponseEntity.ok().body(this.beneficiaryService.createBeneficiary(beneficiary));
	}
	
	@PutMapping("/beneficiary/{id}")
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable long id, @RequestBody Beneficiary beneficiary){
		beneficiary.setId(id);
		return ResponseEntity.ok().body(this.beneficiaryService.updateBeneficiary(beneficiary));
	}

	@DeleteMapping("/beneficiary/{accountid}/{beneaccountid}")
	public HttpStatus deleteBeneficiary(@PathVariable long accountId,long beneAccountId){
		this.beneficiaryService.deleteBeneficiary(accountId,beneAccountId);
		return HttpStatus.OK;
	}
}
