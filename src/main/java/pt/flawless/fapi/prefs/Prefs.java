package pt.flawless.fapi.prefs;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link PrefsApi} registado no ServicesManager. */
public final class Prefs {

    private static final String UNAVAILABLE = "serviço de preferências indisponível: verifique o plugin fPrefs";

    private Prefs() {}

    /**
     * Devolve o {@link PrefsApi} registado pelo plugin fPrefs.
     *
     * <p>Chame no {@code onEnable} (com o fPrefs em {@code depend}) e guarde a instância.
     *
     * @throws PrefsUnavailableException se o fPrefs estiver ausente ou desativado
     */
    public static PrefsApi get() {
        return get(() -> Bukkit.getServicesManager().load(PrefsApi.class));
    }

    static PrefsApi get(Supplier<PrefsApi> lookup) {
        PrefsApi api = lookup.get();
        if (api == null) {
            throw new PrefsUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
