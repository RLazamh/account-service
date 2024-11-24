package com.pichincha.accountservice.application.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionReportDTO {
    private String date;
    private String type;
    private Double value;
    private Double balance;
}
