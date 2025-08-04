package pt.flawless.fapi.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalDatabase implements IDatabaseSetup {
    private final File dbFile;
    private Connection connection;
    private String sqlCreateTablesScript;

    public LocalDatabase(File pluginDataFolder, String fileName, String sqlCreateTablesScript) {
        this.dbFile = new File(pluginDataFolder, fileName + ".db");
        this.sqlCreateTablesScript = sqlCreateTablesScript;
    }

    @Override
    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) return;

        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        connection = DriverManager.getConnection(url);

        createTables();
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    private void createTables() throws SQLException {
        if (this.sqlCreateTablesScript.isEmpty()) {
            throw new SQLException("Create tables script not defined!");
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(this.sqlCreateTablesScript);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
