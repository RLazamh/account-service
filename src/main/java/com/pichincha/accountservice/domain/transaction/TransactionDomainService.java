package com.pichincha.accountservice.domain.transaction;

import com.pichincha.accountservice.application.transaction.exceptions.InsufficientBalanceException;
import com.pichincha.accountservice.application.transaction.exceptions.InvalidTransactionTypeException;
import com.pichincha.accountservice.application.transaction.exceptions.TransactionNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TransactionDomainService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public <T> T validateTransactionExists(Optional<T> transaction, Long id) {
        return transaction.orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public void validateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
    }

    public LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public BigDecimal calculateNewBalance(
            BigDecimal currentBalance,
            BigDecimal transactionValue,
            String transactionType,
            String accountNumber
    ) {
        if (transactionValue == null || transactionValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Transaction value cannot be null or negative.");
        }

        return switch (transactionType.toUpperCase()) {
            case "DEPOSIT" -> currentBalance.add(transactionValue);
            case "WITHDRAWAL" -> {
                if (currentBalance.compareTo(transactionValue) < 0) {
                    throw new InsufficientBalanceException(accountNumber, currentBalance, transactionValue);
                }
                yield currentBalance.subtract(transactionValue);
            }
            default -> throw new InvalidTransactionTypeException(transactionType);
        };
    }
}

