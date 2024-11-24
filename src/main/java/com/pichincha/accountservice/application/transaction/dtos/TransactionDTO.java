package com.pichincha.accountservice.application.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private Long id;
    private Long accountId;
    private String type;
    private BigDecimal value;
    private BigDecimal balance;
    private LocalDateTime date;

    public TransactionDTO() {}
}
