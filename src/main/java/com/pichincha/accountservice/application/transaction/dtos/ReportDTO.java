package com.pichincha.accountservice.application.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {
    private String clientName;
    private List<AccountReportDTO> accounts;
}

