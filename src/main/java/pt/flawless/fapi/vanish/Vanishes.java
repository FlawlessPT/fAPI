package pt.flawless.fapi.vanish;

import org.bukkit.Bukkit;

/** Ponto de acesso ao {@link VanishApi} registado no ServicesManager. */
public final class Vanishes {

    private Vanishes() {}

    /** Devolve o {@link VanishApi} ou {@code null} se o fUtilities estiver ausente (dependência opcional). */
    public static VanishApi get() {
        return Bukkit.getServicesManager().load(VanishApi.class);
    }
}
