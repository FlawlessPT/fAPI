package pt.flawless.fapi.database;

import java.sql.SQLException;

/**
 * Função que pode lançar {@link SQLException}, usada em {@link DatabaseService#supplyAsync}.
 * Se lançar, o futuro devolvido completa excecionalmente com a mesma exceção.
 */
@FunctionalInterface
public interface SqlFunction<T, R> {
    /**
     * @param input o argumento (a ligação do pool, válida apenas durante esta chamada)
     * @return o resultado
     * @throws SQLException em caso de erro de base de dados; é propagada ao futuro tal como está
     */
    R apply(T input) throws SQLException;
}
