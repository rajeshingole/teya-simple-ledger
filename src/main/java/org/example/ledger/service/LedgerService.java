package org.example.ledger;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class LedgerService {
    private final List<TransactionRecord> transactions = new ArrayList<>();
    private long nextId = 1L;
    private BigDecimal balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public synchronized TransactionRecord recordMovement(MovementRequest request) {
        BigDecimal amount = normalize(request.amount());
        if (amount.signum() <= 0) {
            throw new LedgerException("amount must be greater than zero");
        }

        if (request.type() == MovementType.WITHDRAWAL && balance.compareTo(amount) < 0) {
            throw new LedgerException("insufficient funds");
        }

        balance = request.type() == MovementType.DEPOSIT
                ? balance.add(amount)
                : balance.subtract(amount);

        TransactionRecord record = new TransactionRecord(
                nextId++,
                request.type(),
                amount,
                request.description(),
                balance,
                Instant.now()
        );
        transactions.add(record);
        return record;
    }

    public synchronized BigDecimal currentBalance() {
        return balance;
    }

    public synchronized List<TransactionRecord> history() {
        return List.copyOf(transactions);
    }

    private BigDecimal normalize(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
