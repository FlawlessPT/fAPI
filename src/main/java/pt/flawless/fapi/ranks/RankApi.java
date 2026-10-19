package pt.flawless.fapi.ranks;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Contrato do rankup, registado no ServicesManager pelo plugin fRankup.
 *
 * <p>Os futuros completam fora da thread principal.
 */
public interface RankApi {

    /** Rank atual do jogador, já com cores, por exemplo {@code §7Iron 5}. */
    CompletableFuture<String> getRankLabel(UUID uuid);
}
