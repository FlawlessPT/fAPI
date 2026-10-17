package pt.flawless.fapi.permissions;

/**
 * Resultado da verificação de permissão de um jogador. O campo {@code source} indica onde a permissão vem.
 *
 * @param granted {@code true} se a permissão foi concedida
 * @param source origem da permissão: {@code "grupo:<nome>"}, {@code "jogador"} ou {@code null} se nenhuma entrada a
 *     cobre
 */
public record PermissionCheck(boolean granted, String source) {}
