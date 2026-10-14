package pt.flawless.fapi.events.PlayerAuthEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pt.flawless.fapi.enums.FAuthType;

public class PlayerAuthEventImpl {
    public static void callPlayerAuthEvent(Player player, FAuthType authType) {
        PlayerAuthEvent authEvent = new PlayerAuthEvent(player, authType);
        Bukkit.getServer().getPluginManager().callEvent(authEvent);
    }
}
