package pt.flawless.fapi.sounds;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FSound {
    public static void success(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f);
    }

    public static void fail(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 2f);
    }

    public static void sendSound(Player p, Sound sound) {
        p.playSound(p.getLocation(), sound, 1f, 2f);
    }
}
