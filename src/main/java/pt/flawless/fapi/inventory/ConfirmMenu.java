package pt.flawless.fapi.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pt.flawless.fapi.enums.FInventorySize;
import pt.flawless.fapi.items.FItemStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * Menu de confirmação de 27 slots: item de contexto ao centro, botão de confirmar à esquerda, de negar à direita
 * e, opcionalmente, um botão de voltar por baixo. Cada botão tem material, nome, lore e ação próprios.
 * <p>
 * Ao clicar num botão o menu fecha e só depois corre a ação (na thread principal), que pode abrir outro menu.
 * Quem fecha o menu sem clicar não dispara nada. Os cliques são tratados pelo {@link Handler}, registado pelo fAPI.
 */
public final class ConfirmMenu {
    public static final int SUBJECT_SLOT = 4;
    public static final int CONFIRM_SLOT = 11;
    public static final int DENY_SLOT = 15;
    public static final int BACK_SLOT = 22;

    /** Um botão: o item e o que fazer ao clicar. */
    public record Button(Material material, String name, List<String> lore, Consumer<Player> action) {
        public Button {
            lore = lore == null ? List.of() : List.copyOf(lore);
        }

        public Button(Material material, String name, Consumer<Player> action) {
            this(material, name, List.of(), action);
        }

        ItemStack item() {
            return new FItemStack(material, 1, name).setLore(lore).createItem();
        }
    }

    private ConfirmMenu() {}

    /**
     * Abre o menu a {@code player}. Deve ser chamado na thread principal.
     *
     * @param title   título do inventário (cortado a {@link FInventory#MAX_TITLE_LENGTH})
     * @param subject item de contexto no centro (por exemplo a cabeça do jogador); {@code null} deixa o slot vazio
     * @param confirm botão de confirmar
     * @param deny    botão de negar
     * @param back    botão de voltar; {@code null} para não o mostrar
     */
    public static void open(Player player, String title, ItemStack subject, Button confirm, Button deny, Button back) {
        MenuHolder holder = new MenuHolder(confirm, deny, back);
        Inventory inv = new FInventory(holder, FInventorySize.SIZE_27, title).getInventory();
        holder.inventory = inv;
        ItemStack glass = new FItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, " ").createItem();
        for (int slot = 0; slot < inv.getSize(); slot++) inv.setItem(slot, glass);
        if (subject != null) inv.setItem(SUBJECT_SLOT, subject);
        inv.setItem(CONFIRM_SLOT, confirm.item());
        inv.setItem(DENY_SLOT, deny.item());
        if (back != null) inv.setItem(BACK_SLOT, back.item());
        player.openInventory(inv);
    }

    /** Sem botão de voltar. */
    public static void open(Player player, String title, ItemStack subject, Button confirm, Button deny) {
        open(player, title, subject, confirm, deny, null);
    }

    private static final class MenuHolder implements InventoryHolder {
        final Button confirm;
        final Button deny;
        final Button back;
        Inventory inventory;

        MenuHolder(Button confirm, Button deny, Button back) {
            this.confirm = confirm;
            this.deny = deny;
            this.back = back;
        }

        @Override
        public Inventory getInventory() {
            return inventory;
        }
    }

    /** Listener a registar uma vez (feito pelo plugin fAPI). */
    public static final class Handler implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent e) {
            if (!(e.getInventory().getHolder() instanceof MenuHolder holder)) return;
            e.setCancelled(true);
            if (e.getRawSlot() >= e.getInventory().getSize() || !(e.getWhoClicked() instanceof Player p)) return;
            Button clicked = switch (e.getRawSlot()) {
                case CONFIRM_SLOT -> holder.confirm;
                case DENY_SLOT -> holder.deny;
                case BACK_SLOT -> holder.back;
                default -> null;
            };
            if (clicked == null || clicked.action() == null) return;
            p.closeInventory();
            clicked.action().accept(p);
        }

        @EventHandler
        public void onDrag(InventoryDragEvent e) {
            if (e.getInventory().getHolder() instanceof MenuHolder) e.setCancelled(true);
        }
    }
}
