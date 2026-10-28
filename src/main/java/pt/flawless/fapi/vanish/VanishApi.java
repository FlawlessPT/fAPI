package pt.flawless.fapi.vanish;

import java.util.UUID;
import org.bukkit.entity.Player;

/** Contrato do vanish, implementado pelo fUtilities. Só jogadores online; o estado vive em memória. */
public interface VanishApi {

    boolean isVanished(UUID uuid);

    /** Liga ou desliga o vanish de forma idempotente (não alterna). */
    void setVanished(Player player, boolean vanished);
}
