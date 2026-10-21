package pt.flawless.fapi.items;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link ItemsApi} registado no ServicesManager. */
public final class Items {

    private static final String UNAVAILABLE = "serviço de itens indisponível: verifique o plugin fCustomItems";

    private Items() {
    }

    /**
     * Devolve o {@link ItemsApi} registado pelo plugin fCustomItems.
     *
     * @throws ItemsUnavailableException se o fCustomItems estiver ausente ou desativado
     */
    public static ItemsApi get() {
        return get(() -> Bukkit.getServicesManager().load(ItemsApi.class));
    }

    static ItemsApi get(Supplier<ItemsApi> lookup) {
        ItemsApi api = lookup.get();
        if (api == null) {
            throw new ItemsUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
