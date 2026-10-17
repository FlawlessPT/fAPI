package pt.flawless.fapi.economy;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(UUID uuid) {
        super("conta de economia não encontrada: " + uuid);
    }
}
