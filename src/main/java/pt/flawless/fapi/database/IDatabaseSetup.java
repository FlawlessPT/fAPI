package pt.flawless.fapi.database;

import java.sql.SQLException;

/**
 * @deprecated usar {@link DatabaseService}.
 */
@Deprecated
public interface IDatabaseSetup {
    void connect() throws SQLException;
    void close() throws SQLException;
}