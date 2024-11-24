package com.pichincha.accountservice.domain.account;

import com.pichincha.accountservice.application.account.exceptions.AccountNotFoundException;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountDomainService {

    public void validateAccount(AccountEntity accountEntity) {
        if (accountEntity == null) {
            throw new AccountNotFoundException(-1L);
        }

        if (accountEntity.getInitialBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }

    public void updateAccountBalance(AccountEntity accountEntity, BigDecimal amount) {
        BigDecimal newBalance = accountEntity.getBalance().add(amount);
        accountEntity.setBalance(newBalance);
    }

}
