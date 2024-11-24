package com.pichincha.accountservice.infrastructure.db.account.repositories;

import com.pichincha.accountservice.infrastructure.db.account.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByClientId(Long clientId);
}
