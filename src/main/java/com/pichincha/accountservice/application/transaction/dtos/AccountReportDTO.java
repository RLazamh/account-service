package com.pichincha.accountservice.application.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AccountReportDTO {
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private BigDecimal availableBalance;
    private List<TransactionReportDTO> transactions;
}
