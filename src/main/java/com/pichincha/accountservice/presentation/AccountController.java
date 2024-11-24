package com.pichincha.accountservice.presentation;

import com.pichincha.accountservice.application.account.CrudAccountUseCase;
import com.pichincha.accountservice.application.account.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final CrudAccountUseCase accountUseCase;

    public AccountController(CrudAccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(
            @RequestBody AccountDTO accountDTO
    ) {
        AccountDTO createdAccount = accountUseCase.createAccount(accountDTO);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(
            @PathVariable("id") Long id
    ) {
        AccountDTO account = accountUseCase.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable("id") Long id,
            @RequestBody AccountDTO accountDTO
    ) {
        AccountDTO updatedAccount = accountUseCase.updateAccount(id, accountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable("id") Long id
    ) {
        accountUseCase.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountUseCase.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
