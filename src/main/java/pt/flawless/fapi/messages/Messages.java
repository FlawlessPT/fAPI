package pt.flawless.fapi.messages;

import org.bukkit.command.CommandSender;

/** Mensagens comuns a todos os plugins, para o texto ser igual em todo o servidor. */
public final class Messages {
    public static final String NO_PERMISSION = "§cNão tens permissão.";

    private Messages() {
    }

    /** Envia {@link #NO_PERMISSION} a quem tentou usar algo sem permissão. */
    public static void noPermission(CommandSender sender) {
        sender.sendMessage(NO_PERMISSION);
    }
}
