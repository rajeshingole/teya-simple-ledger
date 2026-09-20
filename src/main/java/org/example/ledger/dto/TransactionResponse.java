package org.example.ledger;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
        long id,
        MovementType type,
        BigDecimal amount,
        String description,
        BigDecimal balanceAfter,
        Instant occurredAt
) {
    public static TransactionResponse from(TransactionRecord record) {
        return new TransactionResponse(
                record.id(),
                record.type(),
                record.amount(),
                record.description(),
                record.balanceAfter(),
                record.occurredAt()
        );
    }
}
