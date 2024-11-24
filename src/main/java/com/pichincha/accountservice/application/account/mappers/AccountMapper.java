package com.pichincha.accountservice.application.account.mappers;

import com.pichincha.accountservice.application.account.dtos.AccountDTO;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountDTO accountDTO) {
        AccountEntity accountEntity = new AccountEntity();
        mapCommonFields(accountDTO, accountEntity);
        return accountEntity;
    }

    public AccountDTO toDTO(AccountEntity accountEntity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(accountEntity.getId());
        mapCommonFields(accountEntity, accountDTO);
        return accountDTO;
    }

    private void mapCommonFields(AccountDTO accountDTO, AccountEntity accountEntity) {
        accountEntity.setAccountNumber(accountDTO.getAccountNumber());
        accountEntity.setAccountType(accountDTO.getAccountType());
        accountEntity.setInitialBalance(accountDTO.getInitialBalance());
        accountEntity.setStatus(accountDTO.getStatus());
        accountEntity.setClientId(accountDTO.getClientId());
        accountEntity.setBalance(accountDTO.getInitialBalance());
    }

    private void mapCommonFields(AccountEntity accountEntity, AccountDTO accountDTO) {
        accountDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountDTO.setAccountType(accountEntity.getAccountType());
        accountDTO.setInitialBalance(accountEntity.getInitialBalance());
        accountDTO.setStatus(accountEntity.getStatus());
        accountDTO.setClientId(accountEntity.getClientId());
        accountDTO.setBalance(accountEntity.getBalance());
    }
}
