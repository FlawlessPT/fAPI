package pt.flawless.fapi.friends;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link FriendsApi} registado no ServicesManager. */
public final class Friends {

    private static final String UNAVAILABLE = "serviço de amigos indisponível: verifique o plugin fFriends";

    private Friends() {}

    /**
     * Devolve o {@link FriendsApi} registado pelo plugin fFriends.
     *
     * <p>Chame no {@code onEnable} (com o fFriends em {@code depend}) e guarde a instância.
     *
     * @throws FriendsUnavailableException se o fFriends estiver ausente ou desativado
     */
    public static FriendsApi get() {
        return get(() -> Bukkit.getServicesManager().load(FriendsApi.class));
    }

    static FriendsApi get(Supplier<FriendsApi> lookup) {
        FriendsApi api = lookup.get();
        if (api == null) {
            throw new FriendsUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
