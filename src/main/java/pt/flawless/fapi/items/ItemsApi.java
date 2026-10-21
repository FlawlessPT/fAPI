package pt.flawless.fapi.items;

import java.util.Optional;
import java.util.Set;
import org.bukkit.inventory.ItemStack;

/**
 * Contrato dos itens personalizados, implementado e registado no ServicesManager pelo fCustomItems.
 * Os ids não distinguem maiúsculas de minúsculas.
 */
public interface ItemsApi {

    /** Cria uma cópia nova do item com a quantidade configurada ({@code quantidade}); vazio se o id não existir. */
    Optional<ItemStack> create(String id);

    /** Cria uma cópia nova do item com a quantidade indicada; vazio se o id não existir. */
    Optional<ItemStack> create(String id, int amount);

    /** Indica se existe um item com este id. */
    boolean exists(String id);

    /** Devolve o id do item personalizado, ou vazio se não for um (aceita {@code null}). */
    Optional<String> idOf(ItemStack item);

    /** Todos os ids, com a grafia original; o conjunto não é modificável. */
    Set<String> ids();
}
