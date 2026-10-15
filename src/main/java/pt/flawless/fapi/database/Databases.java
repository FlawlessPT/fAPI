package pt.flawless.fapi.database;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link DatabaseService} registado no ServicesManager. */
public final class Databases {

    private static final String UNAVAILABLE =
            "serviço de base de dados indisponível: verifique o plugin fDatabase e a sua configuração";

    private Databases() {
    }

    /**
     * Devolve o {@link DatabaseService} registado pelo plugin fDatabase.
     *
     * <p>Chame no {@code onEnable} (com o fDatabase em {@code depend}) e guarde a instância.
     *
     * @return o serviço; nunca {@code null}
     * @throws DatabaseUnavailableException se o fDatabase estiver ausente ou tiver falhado ao arrancar
     *         (por exemplo, base de dados inacessível): o consumidor deve apanhá-la no {@code onEnable}
     *         e desativar-se
     */
    public static DatabaseService get() {
        return get(() -> Bukkit.getServicesManager().load(DatabaseService.class));
    }

    static DatabaseService get(Supplier<DatabaseService> lookup) {
        DatabaseService service = lookup.get();
        if (service == null) {
            throw new DatabaseUnavailableException(UNAVAILABLE);
        }
        return service;
    }
}
