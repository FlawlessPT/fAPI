package pt.flawless.fapi.permissions;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link PermissionsApi} registado no ServicesManager. */
public final class Permissions {

    private static final String UNAVAILABLE =
            "serviço de permissões indisponível: verifique o plugin fPermissions";

    private Permissions() {}

    /**
     * Devolve o {@link PermissionsApi} registado pelo plugin fPermissions.
     *
     * <p>Chame no {@code onEnable} (com o fPermissions em {@code depend}) e guarde a instância.
     *
     * @throws PermissionsUnavailableException se o fPermissions estiver ausente ou desativado
     */
    public static PermissionsApi get() {
        return get(() -> Bukkit.getServicesManager().load(PermissionsApi.class));
    }

    static PermissionsApi get(Supplier<PermissionsApi> lookup) {
        PermissionsApi api = lookup.get();
        if (api == null) {
            throw new PermissionsUnavailableException(UNAVAILABLE);
        }
        return api;
    }
}
