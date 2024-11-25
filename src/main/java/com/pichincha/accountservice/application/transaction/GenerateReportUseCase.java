package com.pichincha.accountservice.application.transaction;

import com.pichincha.accountservice.application.transaction.dtos.AccountReportDTO;
import com.pichincha.accountservice.application.transaction.dtos.ReportDTO;
import com.pichincha.accountservice.application.transaction.mappers.ReportMapper;
import com.pichincha.accountservice.domain.transaction.TransactionDomainService;
import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import com.pichincha.accountservice.infrastructure.db.account.services.AccountDbService;
import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import com.pichincha.accountservice.infrastructure.db.transaction.services.TransactionDbService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateReportUseCase {

    private final AccountDbService accountDbService;
    private final TransactionDbService transactionDbService;
    private final TransactionDomainService transactionDomainService;
    private final ReportMapper reportMapper;

    public GenerateReportUseCase(
            AccountDbService accountDbService,
            TransactionDbService transactionDbService,
            TransactionDomainService transactionDomainService,
            ReportMapper reportMapper
    ) {
        this.accountDbService = accountDbService;
        this.transactionDbService = transactionDbService;
        this.transactionDomainService = transactionDomainService;
        this.reportMapper = reportMapper;
    }

    public ReportDTO generateReport(Long clientId, String startDate, String endDate) {
        LocalDateTime start = transactionDomainService.parseDate(startDate);
        LocalDateTime end = transactionDomainService.parseDate(endDate);

        List<AccountEntity> accounts = accountDbService.findAccountsByClientId(clientId);

        List<AccountReportDTO> accountReports = accounts.stream()
                .map(account -> {
                    List<TransactionEntity> transactions = transactionDbService.findTransactionsByAccountAndDateRange(
                            account.getId(),
                            start,
                            end
                    );
                    return reportMapper.toAccountReportDTO(account, transactions);
                })
                .collect(Collectors.toList());

        return buildReport(clientId, accountReports);
    }

    private ReportDTO buildReport(Long clientId, List<AccountReportDTO> accountReports) {
        ReportDTO report = new ReportDTO();
        report.setClientName(getClientName(clientId));
        report.setAccounts(accountReports);
        return report;
    }

    private String getClientName(Long clientId) {
        return "Client Identification: " + clientId;
    }
}
