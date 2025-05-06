package pt.flawless.fapi.title;

import net.minecraft.server.v1_8_R1.EnumTitleAction;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R1.ChatComponentText;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;

public class FTitle {
    private Player player;
    private String title = "§c§lAVISO";
    private String message = "";
    private int time = 100; // seconds

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
        IChatBaseComponent titleComponent = new ChatComponentText(this.title);
        IChatBaseComponent subtitleComponent = new ChatComponentText(this.message);

        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleComponent);
        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleComponent);
        PacketPlayOutTitle lengthPacket = new PacketPlayOutTitle(5, this.time, 5);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(lengthPacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlePacket);

        return this;
    }

    public FTitle clear() {
        PacketPlayOutTitle clearTitlePacket = new PacketPlayOutTitle(
                EnumTitleAction.RESET, null
        );

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(clearTitlePacket);

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
