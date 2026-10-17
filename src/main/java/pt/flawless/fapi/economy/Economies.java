package pt.flawless.fapi.economy;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link EconomyApi} registado no ServicesManager. */
public final class Economies {

    private static final String UNAVAILABLE = "serviço de economia indisponível: verifique o plugin fEconomy";

    private Economies() {
    }

    /**
     * Devolve o {@link EconomyApi} registado pelo plugin fEconomy.
     *
     * <p>Chame no {@code onEnable} (com o fEconomy em {@code depend}) e guarde a instância.
     *
     * @throws EconomyUnavailableException se o fEconomy estiver ausente ou desativado
     */
    public static EconomyApi get() {
        return get(() -> Bukkit.getServicesManager().load(EconomyApi.class));
    }

    static EconomyApi get(Supplier<EconomyApi> lookup) {
        EconomyApi api = lookup.get();
        if (api == null) {
            throw new EconomyUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
