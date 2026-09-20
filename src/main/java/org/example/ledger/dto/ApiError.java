package org.example.ledger;

import java.time.Instant;

public record ApiError(String message, Instant timestamp) {
}
