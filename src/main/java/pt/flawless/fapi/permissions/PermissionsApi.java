package pt.flawless.fapi.permissions;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Contrato de permissões, registado no ServicesManager pelo plugin fPermissions.
 *
 * <p>Os futuros completam fora da thread principal do servidor. Falham com {@link IllegalArgumentException} (grupo ou
 * nó inválido); as exceções podem chegar embrulhadas numa {@code CompletionException}.
 */
public interface PermissionsApi {

    /**
     * Verifica se um jogador tem uma permissão (síncrono, a partir da cache).
     *
     * @param uuid ID do jogador
     * @param permission nome da permissão (por exemplo {@code "plugin.comando"})
     * @return {@code true} se tem a permissão; {@code false} se não tem ou o jogador não está carregado
     */
    boolean has(UUID uuid, String permission);

    /**
     * Verifica se um jogador tem uma permissão (assíncrono, lê da base de dados).
     *
     * @param uuid ID do jogador
     * @param permission nome da permissão
     * @return futuro com {@link PermissionCheck} contendo a decisão e a origem
     */
    CompletableFuture<PermissionCheck> check(UUID uuid, String permission);

    /**
     * Devolve os grupos atribuídos explicitamente ao jogador (não herança).
     *
     * @param uuid ID do jogador
     * @return futuro com conjunto de nomes de grupo
     */
    CompletableFuture<Set<String>> getGroups(UUID uuid);

    /**
     * Adiciona um grupo ao jogador.
     *
     * @param uuid ID do jogador
     * @param group nome do grupo (deve existir na configuração)
     * @return futuro com {@code true} se algo mudou, {@code false} se o jogador já tinha o grupo
     * @throws IllegalArgumentException se o grupo não existe
     */
    CompletableFuture<Boolean> addGroup(UUID uuid, String group);

    /**
     * Remove um grupo do jogador.
     *
     * @param uuid ID do jogador
     * @param group nome do grupo
     * @return futuro com {@code true} se algo mudou, {@code false} se o jogador não tinha o grupo
     */
    CompletableFuture<Boolean> removeGroup(UUID uuid, String group);

    /**
     * Devolve as permissões atribuídas explicitamente ao jogador (não de grupos, não herança).
     *
     * @param uuid ID do jogador
     * @return futuro com conjunto de permissões
     */
    CompletableFuture<Set<String>> getPlayerPermissions(UUID uuid);

    /**
     * Adiciona uma permissão ao jogador.
     *
     * @param uuid ID do jogador
     * @param permission nome da permissão
     * @return futuro com {@code true} se algo mudou, {@code false} se o jogador já tinha a permissão
     * @throws IllegalArgumentException se o nome da permissão é inválido
     */
    CompletableFuture<Boolean> addPlayerPermission(UUID uuid, String permission);

    /**
     * Remove uma permissão do jogador.
     *
     * @param uuid ID do jogador
     * @param permission nome da permissão
     * @return futuro com {@code true} se algo mudou, {@code false} se o jogador não tinha a permissão
     */
    CompletableFuture<Boolean> removePlayerPermission(UUID uuid, String permission);

    /**
     * Devolve os nomes de todos os grupos (síncronos).
     *
     * @return conjunto com os nomes dos grupos configurados
     */
    Set<String> getGroupNames();

    /**
     * Devolve as informações de um grupo (síncronos).
     *
     * @param name nome do grupo
     * @return {@link Optional} contendo as informações, vazio se o grupo não existe
     */
    Optional<GroupInfo> getGroup(String name);
}
