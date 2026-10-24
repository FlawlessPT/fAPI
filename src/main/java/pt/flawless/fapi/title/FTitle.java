package pt.flawless.fapi.title;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;

public class FTitle {
    private Player player;
    private String title = "§c§lAVISO";
    private String message = "";
    private int time = 100; // ticks

    public FTitle() {}

    public FTitle(String message) {
        this.message = message;
    }

    public FTitle(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public FTitle(String title, String message, int time) {
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public FTitle send() {
        LegacyComponentSerializer legacy = LegacyComponentSerializer.legacySection();
        // 5 ticks (250 ms) de entrada e saída, como antes; time em ticks
        player.showTitle(Title.title(legacy.deserialize(this.title), legacy.deserialize(this.message),
                Title.Times.times(Duration.ofMillis(250), Duration.ofMillis(this.time * 50L), Duration.ofMillis(250))));

        return this;
    }

    public FTitle clear() {
        player.resetTitle();

        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public FTitle setPlayer(Player player) {
        this.player = player;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public FTitle setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public FTitle setMessage(String message) {
        this.message = message;

        return this;
    }

    public int getTime() {
        return time;
    }

    public FTitle setTime(int time) {
        this.time = time;

        return this;
    }
}
