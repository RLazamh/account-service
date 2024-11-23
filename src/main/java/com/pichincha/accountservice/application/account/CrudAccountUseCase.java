package com.pichincha.accountservice.application.account;

import com.pichincha.accountservice.application.account.dtos.AccountDTO;
import com.pichincha.accountservice.application.account.exceptions.AccountNotFoundException;
import com.pichincha.accountservice.application.account.mappers.AccountMapper;
import com.pichincha.accountservice.infrastructure.db.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.services.AccountDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudAccountUseCase {

    private final AccountDbService accountDbService;
    private final AccountMapper accountMapper;

    public CrudAccountUseCase(AccountDbService accountDbService, AccountMapper accountMapper) {
        this.accountDbService = accountDbService;
        this.accountMapper = accountMapper;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountEntity accountEntity = accountMapper.toEntity(accountDTO);
        AccountEntity savedEntity = accountDbService.saveAccount(accountEntity);
        return accountMapper.toDTO(savedEntity);
    }

    public AccountDTO getAccountById(Long id) {
        AccountEntity accountEntity = findAccountOrThrow(id);
        return accountMapper.toDTO(accountEntity);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        AccountEntity existingEntity = findAccountOrThrow(id);
        accountMapper.updateEntityFromDTO(accountDTO, existingEntity);
        AccountEntity updatedEntity = accountDbService.saveAccount(existingEntity);
        return accountMapper.toDTO(updatedEntity);
    }

    public void deleteAccount(Long id) {
        findAccountOrThrow(id);
        accountDbService.deleteAccountById(id);
    }

    public List<AccountDTO> getAllAccounts() {
        List<AccountEntity> accounts = accountDbService.findAllAccounts();
        return accounts.stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    private AccountEntity findAccountOrThrow(Long id) {
        return accountDbService.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
}
