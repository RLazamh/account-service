package com.pichincha.accountservice.infrastructure.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "initial_balance", nullable = false)
    private Double initialBalance;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "client_id", nullable = false)
    private Long clientId;
}
