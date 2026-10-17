package pt.flawless.fapi.economy;

import java.util.UUID;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(UUID uuid, long balance, long required) {
        super("saldo insuficiente na conta " + uuid + ": saldo " + balance
                + " cêntimos, necessário " + required + " cêntimos");
    }
}
