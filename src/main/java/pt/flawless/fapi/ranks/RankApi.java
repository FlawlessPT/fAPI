package pt.flawless.fapi.ranks;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Contrato do rankup, registado no ServicesManager pelo plugin fRankup.
 *
 * <p>Os futuros completam fora da thread principal.
 */
public interface RankApi {

    /** Tag do rank atual do jogador, já com cores, por exemplo {@code §7[IronV]}. */
    CompletableFuture<String> getRankLabel(UUID uuid);

    /** Só o numeral do rank atual, já com a cor do tier, por exemplo {@code §7[V]} (tab). */
    CompletableFuture<String> getRankNumeral(UUID uuid);
}
