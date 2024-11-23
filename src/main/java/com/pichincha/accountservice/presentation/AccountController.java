package com.pichincha.accountservice.presentation;

import com.pichincha.accountservice.application.account.CrudAccountUseCase;
import com.pichincha.accountservice.application.account.dtos.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        AccountDTO createAccount = accountUseCase.createAccount(accountDTO);
        return ResponseEntity.ok(createAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(
            @PathVariable("id") Long id
    ) {
        AccountDTO account = accountUseCase.getAccountById(id);
        return ResponseEntity.ok(account);
    }
}
