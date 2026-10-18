package pt.flawless.fapi.tags;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link TagsApi} registado no ServicesManager. */
public final class Tags {

    private static final String UNAVAILABLE = "serviço de tags indisponível: verifique o plugin fTags";

    private Tags() {
    }

    /**
     * Devolve o {@link TagsApi} registado pelo plugin fTags.
     *
     * <p>Chame no {@code onEnable} (com o fTags em {@code depend}) e guarde a instância.
     *
     * @throws TagsUnavailableException se o fTags estiver ausente ou desativado
     */
    public static TagsApi get() {
        return get(() -> Bukkit.getServicesManager().load(TagsApi.class));
    }

    static TagsApi get(Supplier<TagsApi> lookup) {
        TagsApi api = lookup.get();
        if (api == null) {
            throw new TagsUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
