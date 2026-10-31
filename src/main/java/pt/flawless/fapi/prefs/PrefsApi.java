package pt.flawless.fapi.prefs;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/** Contrato das preferências de jogador, implementado pelo plugin fPrefs. */
public interface PrefsApi {

    /**
     * Regista uma preferência (aparece no menu {@code /prefs}).
     *
     * @throws IllegalStateException se o id já existir ou já houver 28 preferências
     */
    void register(Pref pref);

    /**
     * Opção escolhida pelo jogador. Síncrono, só para jogadores online. Devolve a opção por omissão se o jogador
     * não estiver na cache ou o valor guardado já não existir.
     *
     * @return id da opção
     * @throws IllegalArgumentException se a preferência não existir
     */
    String get(UUID uuid, String prefId);

    /**
     * Guarda a escolha do jogador. Completa fora da thread principal; falha se a preferência ou a opção não
     * existirem ou a gravação falhar. A cache só muda depois de gravar.
     */
    CompletableFuture<Void> set(UUID uuid, String prefId, String optionId);
}
