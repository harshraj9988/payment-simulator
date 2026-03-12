package com.harshraj9988.payment_simulator.entity;


import com.harshraj9988.payment_simulator.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(unique = true, name = "idempotency_key")
    private String idempotencyKey;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
