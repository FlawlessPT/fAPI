package pt.flawless.fapi.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.Test;

class DatabasesTest {

    private static final class StubService implements DatabaseService {
        @Override
        public <T> CompletableFuture<T> supplyAsync(SqlFunction<Connection, T> work) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Void> runAsync(SqlConsumer<Connection> work) {
            return new CompletableFuture<>();
        }

        @Override
        public void migrate(Plugin owner, List<String> resources) {
        }
    }

    @Test
    void get_returnsServiceWhenRegistered() {
        DatabaseService service = new StubService();
        assertSame(service, Databases.get(() -> service));
    }

    @Test
    void get_throwsWhenNoServiceRegistered() {
        DatabaseUnavailableException ex =
                assertThrows(DatabaseUnavailableException.class, () -> Databases.get(() -> null));
        assertTrue(ex.getMessage().contains("fDatabase"));
        assertEquals("serviço de base de dados indisponível: verifique o plugin fDatabase e a sua configuração",
                ex.getMessage());
    }
}
