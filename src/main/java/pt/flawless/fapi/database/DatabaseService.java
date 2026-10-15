package pt.flawless.fapi.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.bukkit.plugin.Plugin;

/** Contrato da camada de base de dados partilhada, implementado pelo plugin fDatabase. */
public interface DatabaseService {

    /** Executa {@code work} numa ligação do pool, fora da thread principal, e devolve o resultado. */
    <T> CompletableFuture<T> supplyAsync(SqlFunction<Connection, T> work);

    /** Executa {@code work} numa ligação do pool, fora da thread principal. */
    CompletableFuture<Void> runAsync(SqlConsumer<Connection> work);

    /** Aplica as migrações SQL (recursos do jar de {@code owner}) ainda não aplicadas, por ordem. */
    void migrate(Plugin owner, List<String> resources) throws SQLException;
}
