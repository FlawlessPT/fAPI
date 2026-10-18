package pt.flawless.fapi.economy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

class EconomiesTest {

    private static final class StubApi implements EconomyApi {
        @Override
        public CompletableFuture<Long> getBalance(UUID uuid) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Long> deposit(UUID uuid, long cents) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Long> withdraw(UUID uuid, long cents) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Long> transfer(UUID from, UUID to, long cents) {
            return new CompletableFuture<>();
        }

        @Override
        public String format(long cents) {
            return cents + "c";
        }
        @Override
        public CompletableFuture<Optional<UUID>> getRichest() {
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    @Test
    void get_returnsServiceWhenRegistered() {
        EconomyApi api = new StubApi();
        assertSame(api, Economies.get(() -> api));
    }

    @Test
    void get_throwsWhenNoServiceRegistered() {
        EconomyUnavailableException ex =
                assertThrows(EconomyUnavailableException.class, () -> Economies.get(() -> null));
        assertEquals("serviço de economia indisponível: verifique o plugin fEconomy", ex.getMessage());
    }
}
