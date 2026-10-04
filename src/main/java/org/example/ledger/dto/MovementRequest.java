package org.example.ledger.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.ledger.model.MovementType;
import java.math.BigDecimal;

public record MovementRequest(
        @NotNull MovementType type,
        @NotNull @Positive BigDecimal amount,
        String description
) {
}
