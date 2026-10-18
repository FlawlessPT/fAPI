package pt.flawless.fapi.tags;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Contrato das tags de chat, implementado e registado no ServicesManager pelo plugin fTags.
 *
 * <p>Os futuros completam fora da thread principal.
 */
public interface TagsApi {

    /** Textos das tags visíveis do jogador (já com {@code §}), pela ordem da config. */
    CompletableFuture<List<String>> getVisibleTags(UUID uuid);

    /**
     * Dá a tag ao jogador. Numa tag {@code unique}, tira-a ao anterior detentor.
     *
     * @return {@code false} se o jogador já a tinha
     * @throws IllegalArgumentException de imediato (síncrono, antes de devolver o futuro) se o tagId for desconhecido
     */
    CompletableFuture<Boolean> give(UUID uuid, String tagId);

    /**
     * Tira a tag ao jogador.
     *
     * @return {@code false} se o jogador não a tinha
     * @throws IllegalArgumentException de imediato (síncrono, antes de devolver o futuro) se o tagId for desconhecido
     */
    CompletableFuture<Boolean> take(UUID uuid, String tagId);
}
