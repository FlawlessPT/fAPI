package pt.flawless.fapi;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pt.flawless.fapi.logs.FConsoleLogger;

public final class FAPI extends JavaPlugin {
    private static Plugin plugin;
    ConsoleCommandSender b = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        plugin = this;

        FConsoleLogger.sendEnablePlugin(plugin.getName());
    }

    @Override
    public void onDisable() {
        FConsoleLogger.sendDisablePlugin(plugin.getName());
    }

    public static Plugin getMainPlugin() {
        return plugin;
    }
}
