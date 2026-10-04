package org.example.ledger.model;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionRecord(
        long id,
        MovementType type,
        BigDecimal amount,
        String description,
        BigDecimal balanceAfter,
        Instant occurredAt
) {
}
