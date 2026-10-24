package pt.flawless.fapi.actionbar;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class FActionBar {
    private Player player;
    private String message = "";
    private int time = 10;

    private int x = 0;

    public FActionBar(Player player) {
        this.player = player;
    }

    public FActionBar(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public FActionBar(Player player, String message, int time) {
        this.player = player;
        this.message = message;
        this.time = time;
    }

    public void send() {
        player.sendActionBar(LegacyComponentSerializer.legacySection().deserialize(message));
    }

    public String getMessage() {
        return this.message;
    }


    public FActionBar setMessage(String message) {
        this.message = message;

        return this;
    }


    public int getTime() {
        return this.time;
    }


    public FActionBar setTime(int time) {
        this.time = time;

        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public FActionBar setPlayer(Player player) {
        this.player = player;

        return this;
    }
}
