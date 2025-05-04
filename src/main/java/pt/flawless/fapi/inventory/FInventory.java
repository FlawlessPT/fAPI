package pt.flawless.fapi.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import pt.flawless.fapi.enums.FInventorySize;
import pt.flawless.fapi.items.FItemStack;

public class FInventory {
    private final Inventory inventory;
    private String inventoryTitle = "Menu";

    public FInventory(FInventorySize fInventorySize) {
        this.inventory = Bukkit.createInventory(null, fInventorySize.getSize(), inventoryTitle);
    }

    public FInventory(FInventorySize fInventorySize, String title) {
        this.inventoryTitle = title;
        inventory = Bukkit.createInventory(null, fInventorySize.getSize(), title);
    }

    // TODO: Create FItemStack
    public Inventory setItem(int position, FItemStack item) {
        this.inventory.setItem(position, item.createItem());

        return this.inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
