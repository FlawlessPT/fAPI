package pt.flawless.fapi.events.PlayerAuthEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pt.flawless.fapi.enums.FAuthType;

public class PlayerAuthEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private FAuthType authType;

    public PlayerAuthEvent(Player player, FAuthType authType) {
        this.player = player;
        this.authType = authType;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public FAuthType getAuthType() {
        return authType;
    }

    public void setAuthType(FAuthType authType) {
        this.authType = authType;
    }
}
