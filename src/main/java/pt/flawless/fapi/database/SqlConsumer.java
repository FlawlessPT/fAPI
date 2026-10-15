package pt.flawless.fapi.database;

import java.sql.SQLException;

/** Consumidor que pode lançar {@link SQLException}. */
@FunctionalInterface
public interface SqlConsumer<T> {
    void accept(T input) throws SQLException;
}
