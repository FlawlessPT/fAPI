package pt.flawless.fapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pt.flawless.fapi.logs.FConsoleLogger;

public final class FAPI extends JavaPlugin {
    private static Plugin plugin;
    FConsoleLogger consoleLogger = new FConsoleLogger(this.getName());

    @Override
    public void onEnable() {
        plugin = this;

        consoleLogger.sendEnablePluginMessage();
    }

    @Override
    public void onDisable() {
        consoleLogger.sendDisablePluginMessage();
    }

    public static Plugin getMainPlugin() {
        return plugin;
    }
}
