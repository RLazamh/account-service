package com.pichincha.accountservice.presentation.transaction;

import com.pichincha.accountservice.application.transaction.RegisterTransactionUseCase;
import com.pichincha.accountservice.application.transaction.TransactionQueryUseCase;
import com.pichincha.accountservice.application.transaction.GenerateReportUseCase;
import com.pichincha.accountservice.application.transaction.dtos.TransactionDTO;
import com.pichincha.accountservice.application.transaction.dtos.ReportDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;
    private final TransactionQueryUseCase transactionQueryUseCase;
    private final GenerateReportUseCase generateReportUseCase;

    public TransactionController(
            RegisterTransactionUseCase registerTransactionUseCase,
            TransactionQueryUseCase transactionQueryUseCase,
            GenerateReportUseCase generateReportUseCase
    ) {
        this.registerTransactionUseCase = registerTransactionUseCase;
        this.transactionQueryUseCase = transactionQueryUseCase;
        this.generateReportUseCase = generateReportUseCase;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> registerTransaction(
            @RequestBody TransactionDTO transactionDTO
    ) {
        TransactionDTO registeredTransaction = registerTransactionUseCase.execute(transactionDTO);
        return ResponseEntity.ok(registeredTransaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionQueryUseCase.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(
            @PathVariable("transactionId") Long transactionId
    ) {
        TransactionDTO transactionDTO = transactionQueryUseCase.getTransactionById(transactionId);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUser(
            @RequestParam("clientId") Long clientId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<TransactionDTO> transactions =
                transactionQueryUseCase.getTransactionsByClientAndDateRange(clientId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/report")
    public ResponseEntity<ReportDTO> getReport(
            @RequestParam("clientId") Long clientId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        ReportDTO report = generateReportUseCase.generateReport(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
