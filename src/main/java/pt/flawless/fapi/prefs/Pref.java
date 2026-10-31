package pt.flawless.fapi.prefs;

import java.util.List;
import org.bukkit.Material;

/**
 * Definição de uma preferência de jogador.
 *
 * @param id            identificador único
 * @param name          nome mostrado no menu
 * @param icon          ícone no menu
 * @param description   linhas de descrição
 * @param options       opções possíveis (pelo menos 2)
 * @param defaultOption id da opção por omissão
 */
public record Pref(
        String id,
        String name,
        Material icon,
        List<String> description,
        List<PrefOption> options,
        String defaultOption) {

    public Pref {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id vazio");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nome vazio");
        }
        options = List.copyOf(options);
        description = List.copyOf(description);
        if (options.size() < 2) {
            throw new IllegalArgumentException("são precisas pelo menos 2 opções");
        }
        if (options.stream().noneMatch(o -> o.id().equals(defaultOption))) {
            throw new IllegalArgumentException("opção por omissão inexistente: " + defaultOption);
        }
    }
}
