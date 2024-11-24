package com.pichincha.accountservice.application.transaction.mappers;

import com.pichincha.accountservice.application.transaction.dtos.AccountReportDTO;
import com.pichincha.accountservice.application.transaction.dtos.TransactionReportDTO;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportMapper {

    public AccountReportDTO toAccountReportDTO(
            AccountEntity accountEntity,
            List<TransactionEntity> transactionEntities
    ) {
        List<TransactionReportDTO> transactionReports = transactionEntities.stream()
                .map(this::toTransactionReportDTO)
                .collect(Collectors.toList());

        AccountReportDTO accountReportDTO = new AccountReportDTO();
        accountReportDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountReportDTO.setAccountType(accountEntity.getAccountType());
        accountReportDTO.setInitialBalance(accountEntity.getInitialBalance());
        accountReportDTO.setAvailableBalance(accountEntity.getBalance());
        accountReportDTO.setTransactions(transactionReports);

        return accountReportDTO;
    }

    public TransactionReportDTO toTransactionReportDTO(
            TransactionEntity transactionEntity
    ) {
        TransactionReportDTO transactionReportDTO = new TransactionReportDTO();
        transactionReportDTO.setDate(transactionEntity.getDate().toString());
        transactionReportDTO.setType(transactionEntity.getType());
        transactionReportDTO.setValue(transactionEntity.getValue().doubleValue());
        transactionReportDTO.setBalance(transactionEntity.getBalance().doubleValue());
        return transactionReportDTO;
    }
}
