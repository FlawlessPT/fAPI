package pt.flawless.fapi.chat;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link ChatApi} registado no ServicesManager. */
public final class Chats {

    private static final String UNAVAILABLE = "serviço de chat indisponível: verifique o plugin fChat";

    private Chats() {}

    /**
     * Devolve o {@link ChatApi} registado pelo plugin fChat.
     *
     * <p>Chame no {@code onEnable} (com o fChat em {@code depend}) e guarde a instância.
     *
     * @throws ChatUnavailableException se o fChat estiver ausente ou desativado
     */
    public static ChatApi get() {
        return get(() -> Bukkit.getServicesManager().load(ChatApi.class));
    }

    static ChatApi get(Supplier<ChatApi> lookup) {
        ChatApi api = lookup.get();
        if (api == null) {
            throw new ChatUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
