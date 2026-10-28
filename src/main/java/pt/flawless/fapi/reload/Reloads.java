package pt.flawless.fapi.reload;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

/** Registo e consulta dos {@link Reloadable} no ServicesManager (um por plugin). */
public final class Reloads {

    private Reloads() {
    }

    /** Chame no {@code onEnable}; o Bukkit remove o registo quando o plugin desliga. */
    public static void register(Plugin plugin, Reloadable reloadable) {
        Bukkit.getServicesManager().register(Reloadable.class, reloadable, plugin, ServicePriority.Normal);
    }

    public static List<RegisteredServiceProvider<Reloadable>> all() {
        return List.copyOf(Bukkit.getServicesManager().getRegistrations(Reloadable.class));
    }
}
