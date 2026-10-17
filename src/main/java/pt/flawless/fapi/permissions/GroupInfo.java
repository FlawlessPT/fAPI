package pt.flawless.fapi.permissions;

import java.util.List;

/**
 * Informação sobre um grupo de permissões.
 *
 * @param name nome único do grupo
 * @param inherits lista de grupos que herda
 * @param permissions lista de permissões concedidas diretamente
 */
public record GroupInfo(String name, List<String> inherits, List<String> permissions) {}
