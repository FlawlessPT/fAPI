package pt.flawless.fapi.database;

import java.sql.SQLException;

/** Função que pode lançar {@link SQLException}. */
@FunctionalInterface
public interface SqlFunction<T, R> {
    R apply(T input) throws SQLException;
}
