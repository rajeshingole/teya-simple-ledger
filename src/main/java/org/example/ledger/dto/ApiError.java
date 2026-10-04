package org.example.ledger.dto;

import java.time.Instant;

public record ApiError(String message, Instant timestamp) {
}
