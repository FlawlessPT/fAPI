package pt.flawless.fapi.database;

import java.sql.SQLException;

/**
 * Consumidor que pode lançar {@link SQLException}, usado em {@link DatabaseService#runAsync}.
 * Se lançar, o futuro devolvido completa excecionalmente com a mesma exceção.
 */
@FunctionalInterface
public interface SqlConsumer<T> {
    /**
     * @param input o argumento (a ligação do pool, válida apenas durante esta chamada)
     * @throws SQLException em caso de erro de base de dados; é propagada ao futuro tal como está
     */
    void accept(T input) throws SQLException;
}
