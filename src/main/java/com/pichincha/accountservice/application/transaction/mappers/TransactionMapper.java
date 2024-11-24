package com.pichincha.accountservice.application.transaction.mappers;

import com.pichincha.accountservice.application.transaction.dtos.TransactionDTO;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import com.pichincha.accountservice.presentation.util.BigDecimalUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transactionDTO.getId());

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(transactionDTO.getAccountId());
        transactionEntity.setAccount(accountEntity);

        mapToEntity(transactionDTO, transactionEntity);
        return transactionEntity;
    }

    public TransactionDTO toDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transactionEntity.getId());
        transactionDTO.setAccountId(transactionEntity.getAccount().getId());

        mapToDTO(transactionEntity, transactionDTO);
        return transactionDTO;
    }

    private void mapToEntity(TransactionDTO transactionDTO, TransactionEntity transactionEntity) {
        transactionEntity.setType(transactionDTO.getType());
        transactionEntity.setValue(BigDecimalUtils.roundToTwoDecimals(transactionDTO.getValue()));
        transactionEntity.setBalance(BigDecimalUtils.roundToTwoDecimals(transactionDTO.getBalance()));
        transactionEntity.setDate(
                transactionDTO.getDate() != null ? transactionDTO.getDate() : LocalDateTime.now()
        );
    }

    private void mapToDTO(TransactionEntity transactionEntity, TransactionDTO transactionDTO) {
        transactionDTO.setType(transactionEntity.getType());
        transactionDTO.setValue(BigDecimalUtils.roundToTwoDecimals(transactionEntity.getValue()));
        transactionDTO.setBalance(BigDecimalUtils.roundToTwoDecimals(transactionEntity.getBalance()));
        transactionDTO.setDate(transactionEntity.getDate());
    }
}
