package net.ibank.springboot;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import net.ibank.springboot.exception.ResourceNotFoundException;
import net.ibank.springboot.model.Account;
import net.ibank.springboot.model.Beneficiary;
import net.ibank.springboot.repository.AccountRepository;
import net.ibank.springboot.repository.BeneficiaryRepository;
import net.ibank.springboot.service.BeneficiaryServiceImpl;

@SpringBootTest
public class BeneficiaryServiceImplTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BeneficiaryServiceImpl beneficiaryService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        // Set beneficiary properties

        when(accountRepository.findByAccountId(anyLong())).thenReturn(Optional.of(new Account()));
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);

        Beneficiary createdBeneficiary = beneficiaryService.createBeneficiary(beneficiary);

        assertNotNull(createdBeneficiary);
        // Perform assertions based on your business logic
    }

    @Test
    public void testCreateBeneficiaryInvalidAccount() {
        Beneficiary beneficiary = new Beneficiary();
        // Set beneficiary properties

        when(accountRepository.findByAccountId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> beneficiaryService.createBeneficiary(beneficiary));
    }

  

}
