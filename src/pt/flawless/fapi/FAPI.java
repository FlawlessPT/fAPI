package pt.flawless.fapi;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class FAPI extends JavaPlugin {

    ConsoleCommandSender b = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
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
