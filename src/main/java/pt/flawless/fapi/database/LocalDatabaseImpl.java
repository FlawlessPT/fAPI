package pt.flawless.fapi.database;

import org.bukkit.Bukkit;

import java.io.File;

public class LocalDatabaseImpl {
    public static void init(File dataFolder, ILocalDatabaseInit callback) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        try {
            Class.forName("org.sqlite.JDBC");
            callback.onDatabaseInit();
        } catch (ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("Class Not Found- Error creating database!");
        }
    }
}
