package com.pichincha.accountservice.application.transaction;

import com.pichincha.accountservice.application.account.exceptions.AccountNotFoundException;
import com.pichincha.accountservice.application.transaction.dtos.TransactionDTO;
import com.pichincha.accountservice.application.transaction.mappers.TransactionMapper;
import com.pichincha.accountservice.domain.transaction.TransactionDomainService;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.account.services.AccountDbService;
import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.services.TransactionDbService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegisterTransactionUseCase {

    private final TransactionDbService transactionDbService;
    private final AccountDbService accountDbService;
    private final TransactionMapper transactionMapper;
    private final TransactionDomainService transactionDomainService;

    public RegisterTransactionUseCase(
            TransactionDbService transactionDbService,
            AccountDbService accountDbService,
            TransactionMapper transactionMapper,
            TransactionDomainService transactionDomainService
    ) {
        this.transactionDbService = transactionDbService;
        this.accountDbService = accountDbService;
        this.transactionMapper = transactionMapper;
        this.transactionDomainService = transactionDomainService;
    }

    public TransactionDTO execute(TransactionDTO transactionDTO) {
        System.out.println("Starting transaction process for account: " + transactionDTO.getAccountId());

        AccountEntity accountEntity = accountDbService.findAccountById(transactionDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(transactionDTO.getAccountId()));

        BigDecimal newBalance = transactionDomainService.calculateNewBalance(
                accountEntity.getBalance(),
                transactionDTO.getValue(),
                transactionDTO.getType(),
                accountEntity.getAccountNumber()
        );

        accountEntity.setBalance(newBalance);
        accountDbService.saveAccount(accountEntity);

        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDTO);
        transactionEntity.setBalance(newBalance);
        transactionEntity.setAccount(accountEntity);

        TransactionEntity savedTransaction = transactionDbService.saveTransaction(transactionEntity);

        System.out.println("Transaction completed successfully for account: " + transactionDTO.getAccountId());
        return transactionMapper.toDTO(savedTransaction);
    }
}
