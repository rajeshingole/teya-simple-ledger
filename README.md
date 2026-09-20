# Ledger API

A small in-memory Java web API for recording deposits and withdrawals, viewing the current balance, and listing transaction history.

## Assumptions

- This assessment expects a single shared ledger, not account-specific balances.
- Data lives only in memory and resets when the process stops.
- Transactions are stored in insertion order and returned in chronological order.
- Withdrawals are rejected if they would make the balance negative.
- Amounts must be positive decimal values with two-decimal precision.
- The API is intentionally simple and does not include authentication, persistence, or transfer support.

## Run

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`.

## API

Use TestRequest.http to send the request to different API's in the project. Alternatively swagger available on http://localhost:8080/swagger-ui/index.html 
