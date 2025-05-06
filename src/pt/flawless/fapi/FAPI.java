package pt.flawless.fapi;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class FAPI extends JavaPlugin {
    public static Plugin plugin;
    ConsoleCommandSender b = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        plugin = this;
        b.sendMessage("§e");
        b.sendMessage("§e[fAPI] Plugin ativado com sucesso!");
        b.sendMessage("§e");
    }

    @Override
    public void onDisable() {
        b.sendMessage("§c");
        b.sendMessage("§c[fAPI] Plugin desativado com sucesso!");
        b.sendMessage("§c");
    }
}
