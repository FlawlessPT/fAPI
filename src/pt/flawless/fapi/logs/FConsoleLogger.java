package pt.flawless.fapi.logs;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import pt.flawless.fapi.enums.EMessageTypeEnum;

public class FConsoleLogger {
    private String pluginName;

    public FConsoleLogger() {}

    public FConsoleLogger(String pluginName) {
        this.pluginName = pluginName;
    }
    public void sendEnablePluginMessage() {
        ConsoleCommandSender b = Bukkit.getConsoleSender();
        b.sendMessage("§e");
        b.sendMessage("§e[%plugin_name%] Plugin ativado com sucesso!".replace("%plugin_name%", this.pluginName));
        b.sendMessage("§e");
    }

    public void sendDisablePluginMessage() {
        ConsoleCommandSender b = Bukkit.getConsoleSender();
        b.sendMessage("§c");
        b.sendMessage("§c[%plugin_name%] Plugin desativado com sucesso!".replace("%plugin_name%", this.pluginName));
        b.sendMessage("§c");
    }

    public void sendMessage(String message) {
        ConsoleCommandSender b = Bukkit.getConsoleSender();

        b.sendMessage("§f" + message);
    }

    public void sendMessage(String message, EMessageTypeEnum messageTypeEnum) {
        ConsoleCommandSender b = Bukkit.getConsoleSender();

        String messageColor = "§f";

        switch (messageTypeEnum) {
            case SUCCESS -> messageColor = "§e";
            case ERROR -> messageColor = "§c";
        }

        b.sendMessage(messageColor + message);
    }
}
