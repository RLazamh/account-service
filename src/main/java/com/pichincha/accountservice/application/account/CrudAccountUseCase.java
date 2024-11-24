package com.pichincha.accountservice.application.account;

import com.pichincha.accountservice.application.account.dtos.AccountDTO;
import com.pichincha.accountservice.application.account.exceptions.AccountNotFoundException;
import com.pichincha.accountservice.application.account.mappers.AccountMapper;
import com.pichincha.accountservice.domain.account.AccountDomainService;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.account.services.AccountDbService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudAccountUseCase {

    private final AccountDbService accountDbService;
    private final AccountMapper accountMapper;
    private final AccountDomainService accountDomainService;

    public CrudAccountUseCase(
            AccountDbService accountDbService,
            AccountMapper accountMapper,
            AccountDomainService accountDomainService
    ) {
        this.accountDbService = accountDbService;
        this.accountMapper = accountMapper;
        this.accountDomainService = accountDomainService;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountEntity accountEntity = accountMapper.toEntity(accountDTO);
        accountDomainService.validateAccount(accountEntity);

        AccountEntity savedEntity = accountDbService.saveAccount(accountEntity);
        return accountMapper.toDTO(savedEntity);
    }

    public AccountDTO getAccountById(Long id) {
        AccountEntity accountEntity = findAccountOrThrow(id);
        return accountMapper.toDTO(accountEntity);
    }

    public List<AccountDTO> getAllAccounts() {
        List<AccountEntity> accounts = accountDbService.findAllAccounts();
        return accounts.stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateAccountBalance(Long accountId, BigDecimal amount) {
        AccountEntity accountEntity = findAccountOrThrow(accountId);

        accountDomainService.updateAccountBalance(accountEntity, amount);
        accountDbService.saveAccount(accountEntity);
    }

    private AccountEntity findAccountOrThrow(Long id) {
        return accountDbService.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
}
