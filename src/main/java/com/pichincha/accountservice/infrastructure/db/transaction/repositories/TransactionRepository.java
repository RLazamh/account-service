package com.pichincha.accountservice.infrastructure.db.transaction.repositories;

import com.pichincha.accountservice.infrastructure.db.transaction.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>  {
    @Query("SELECT t FROM TransactionEntity t WHERE t.account.clientId = :clientId AND t.date BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findByClientIdAndDateRange(Long clientId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT t FROM TransactionEntity t WHERE t.account.id = :accountId AND t.date BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findTransactionsByAccountAndDateRange(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
