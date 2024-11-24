package com.pichincha.accountservice.infrastructure.db.transaction.services;

import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionDbService {

    private final TransactionRepository transactionRepository;

    public TransactionDbService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionEntity saveTransaction(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

    public Optional<TransactionEntity> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<TransactionEntity> findTransactionsByClientAndDateRange(
            Long clientId,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        return transactionRepository.findByClientIdAndDateRange(clientId, startDate, endDate);
    }

    public List<TransactionEntity> findAllTransactions() {
        Iterable<TransactionEntity> iterable = transactionRepository.findAll();
        List<TransactionEntity> transactions = new ArrayList<>();
        iterable.forEach(transactions::add);
        return transactions;
    }

    public List<TransactionEntity> findTransactionsByAccountAndDateRange(
            Long accountId,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        return transactionRepository.findTransactionsByAccountAndDateRange(accountId, startDate, endDate);
    }


}
