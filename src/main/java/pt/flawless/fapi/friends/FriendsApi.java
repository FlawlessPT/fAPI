package pt.flawless.fapi.friends;

import java.util.Set;
import java.util.UUID;

/** Contrato do serviço de amigos, implementado e registado no ServicesManager pelo fFriends. */
public interface FriendsApi {

    /**
     * Indica se dois jogadores são amigos. Síncrono, a partir da cache, só para jogadores online.
     *
     * @return {@code false} se {@code a} não estiver carregado
     */
    boolean areFriends(UUID a, UUID b);

    /**
     * Amigos do jogador. Síncrono, a partir da cache, só para jogadores online.
     *
     * @return conjunto vazio se o jogador não estiver carregado
     */
    Set<UUID> getFriends(UUID uuid);

    /**
     * Indica se {@code requester} pode teleportar-se para {@code target} sem pedido (é amigo e
     * {@code target} ativou o teleporte automático). Síncrono, a partir da cache.
     *
     * @return {@code false} se {@code target} não estiver carregado
     */
    boolean canAutoTp(UUID target, UUID requester);
}
