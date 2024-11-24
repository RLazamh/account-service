package com.pichincha.accountservice.application.transaction;

import com.pichincha.accountservice.application.transaction.dtos.TransactionDTO;
import com.pichincha.accountservice.application.transaction.mappers.TransactionMapper;
import com.pichincha.accountservice.domain.transaction.TransactionDomainService;
import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.services.TransactionDbService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionQueryUseCase {

    private final TransactionDbService transactionDbService;
    private final TransactionMapper transactionMapper;
    private final TransactionDomainService transactionDomainService;

    public TransactionQueryUseCase(
            TransactionDbService transactionDbService,
            TransactionMapper transactionMapper,
            TransactionDomainService transactionDomainService
    ) {
        this.transactionDbService = transactionDbService;
        this.transactionMapper = transactionMapper;
        this.transactionDomainService = transactionDomainService;
    }

    public TransactionDTO getTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionDomainService.validateTransactionExists(
                transactionDbService.findTransactionById(id),
                id
        );
        return transactionMapper.toDTO(transactionEntity);
    }

    public List<TransactionDTO> getAllTransactions() {
        List<TransactionEntity> transactions = transactionDbService.findAllTransactions();
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> getTransactionsByClientAndDateRange(Long clientId, LocalDateTime startDate, LocalDateTime endDate) {
        transactionDomainService.validateDateRange(startDate, endDate);
        List<TransactionEntity> transactions = transactionDbService.findTransactionsByClientAndDateRange(clientId, startDate, endDate);
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
