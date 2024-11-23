package com.pichincha.accountservice.infrastructure.db.repositories;

import com.pichincha.accountservice.infrastructure.db.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {}
