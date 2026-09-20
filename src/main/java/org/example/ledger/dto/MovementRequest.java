package org.example.ledger;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MovementRequest(
        @NotNull MovementType type,
        @NotNull @Positive BigDecimal amount,
        String description
) {
}
