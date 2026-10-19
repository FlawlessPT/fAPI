package pt.flawless.fapi.ranks;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link RankApi} registado no ServicesManager. */
public final class Ranks {

    private static final String UNAVAILABLE = "serviço de ranks indisponível: verifique o plugin fRankup";

    private Ranks() {
    }

    /**
     * Devolve o {@link RankApi} registado pelo plugin fRankup.
     *
     * @throws RanksUnavailableException se o fRankup estiver ausente ou desativado
     */
    public static RankApi get() {
        return get(() -> Bukkit.getServicesManager().load(RankApi.class));
    }

    static RankApi get(Supplier<RankApi> lookup) {
        RankApi api = lookup.get();
        if (api == null) {
            throw new RanksUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
