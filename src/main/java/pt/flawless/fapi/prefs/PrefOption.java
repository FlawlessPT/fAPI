package pt.flawless.fapi.prefs;

/**
 * Opção de uma preferência.
 *
 * @param id   identificador da opção
 * @param name nome mostrado, com cores {@code §}
 */
public record PrefOption(String id, String name) {}
