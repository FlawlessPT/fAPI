package pt.flawless.fapi.logs;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class FConsoleLogger {
    public static void sendEnablePlugin(String pluginName) {
        ConsoleCommandSender b = Bukkit.getConsoleSender();
        b.sendMessage("§e");
        b.sendMessage("§e[%plugin_name%] Plugin ativado com sucesso!".replace("%plugin_name%", pluginName));
        b.sendMessage("§e");
    }

    public static void sendDisablePlugin(String pluginName) {
        ConsoleCommandSender b = Bukkit.getConsoleSender();
        b.sendMessage("§c");
        b.sendMessage("§c[%plugin_name%] Plugin desativado com sucesso!".replace("%plugin_name%", pluginName));
        b.sendMessage("§c");
    }
}
