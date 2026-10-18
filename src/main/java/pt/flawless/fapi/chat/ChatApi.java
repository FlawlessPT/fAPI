package pt.flawless.fapi.chat;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/** Contrato do serviço de chat, implementado e registado no ServicesManager pelo fChat. */
public interface ChatApi {

    /**
     * Canal em que o jogador fala por omissão. Síncrono, só para jogadores online.
     *
     * @return o canal do jogador, ou {@link Channel#LOCAL} se for desconhecido
     */
    Channel getDefaultChannel(UUID uuid);

    /**
     * Define o canal por omissão do jogador. Completa fora da thread principal; falha se a
     * gravação falhar.
     */
    CompletableFuture<Void> setDefaultChannel(UUID uuid, Channel channel);
}
