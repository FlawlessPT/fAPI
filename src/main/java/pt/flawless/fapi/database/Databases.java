package pt.flawless.fapi.database;

import java.util.function.Supplier;
import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link DatabaseService} registado no ServicesManager. */
public final class Databases {

    private static final String UNAVAILABLE =
            "serviço de base de dados indisponível: verifique o plugin fDatabase e a sua configuração";

    private Databases() {
    }

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
