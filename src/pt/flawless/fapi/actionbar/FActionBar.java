package pt.flawless.fapi.actionbar;

import net.minecraft.server.v1_8_R1.ChatComponentText;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
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
        IChatBaseComponent chat = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2); // 2 = action bar
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
//        CraftPlayer player = (CraftPlayer) p;
//        IChatBaseComponent chatBaseComponent = ChatSerializer.a("{\"text\": \"" + this.message + "\"}");
//        PacketPlayOutChat packetPlayoutChat = new PacketPlayOutChat(chatBaseComponent, (byte) 2);
//
//        Bukkit.getScheduler().scheduleAsyncRepeatingTask(FAPI.plugin, new Runnable() {
//            @Override
//            public void run() {
//                if ((time / 2) > x) player.getHandle().playerConnection.sendPacket(packetPlayoutChat);
//                x++;
//            }
//        }, 0L, 40L);
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
