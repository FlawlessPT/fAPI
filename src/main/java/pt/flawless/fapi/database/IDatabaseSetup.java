package pt.flawless.fapi.database;

import java.sql.SQLException;

public interface IDatabaseSetup {
    void connect() throws SQLException;
    void close() throws SQLException;
}