package pt.flawless.fapi.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pt.flawless.fapi.enums.FInventorySize;
import pt.flawless.fapi.items.FItemStack;

public class FInventory {
    /** Limite do título de um inventário no Minecraft 1.8. */
    public static final int MAX_TITLE_LENGTH = 32;

    private static final String DEFAULT_TITLE = "Menu";

    private final Inventory inventory;

    public FInventory(FInventorySize fInventorySize) {
        this(null, fInventorySize, DEFAULT_TITLE);
    }

    public FInventory(FInventorySize fInventorySize, String title) {
        this(null, fInventorySize, title);
    }

    /**
     * @param holder identifica o inventário nos eventos ({@code getHolder() instanceof ...}), mais seguro do que
     *               comparar o título; pode ser {@code null}
     */
    public FInventory(InventoryHolder holder, FInventorySize fInventorySize, String title) {
        this.inventory = Bukkit.createInventory(holder, fInventorySize.getSize(), fitTitle(title));
    }

    /** Título nulo passa a "Menu"; títulos acima do limite são cortados, sem deixar um {@code §} solto no fim. */
    static String fitTitle(String title) {
        if (title == null) {
            return DEFAULT_TITLE;
        }
        if (title.length() <= MAX_TITLE_LENGTH) {
            return title;
        }
        String cut = title.substring(0, MAX_TITLE_LENGTH);
        return cut.endsWith("§") ? cut.substring(0, MAX_TITLE_LENGTH - 1) : cut;
    }

    static void checkPosition(int position, int size) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("posição fora do inventário: " + position + " (tamanho " + size + ")");
        }
    }

    public Inventory setItem(int position, FItemStack item) {
        checkPosition(position, this.inventory.getSize());
        this.inventory.setItem(position, item.createItem());

        return this.inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
