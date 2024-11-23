package com.pichincha.accountservice.application.account.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private Long clientId;
}
