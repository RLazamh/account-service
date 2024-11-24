package com.pichincha.accountservice.infrastructure.db.account.services;

import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.account.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountDbService {

    private final AccountRepository accountRepository;

    public AccountDbService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    public Optional<AccountEntity> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<AccountEntity> findAllAccounts() {
        Iterable<AccountEntity> iterable = accountRepository.findAll();
        List<AccountEntity> accounts = new ArrayList<>();
        iterable.forEach(accounts::add);
        return accounts;
    }

    public List<AccountEntity> findAccountsByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }
}
