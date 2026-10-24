package pt.flawless.fapi.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FItemStack {
    private Material material;
    private String displayName;
    private int quantity;
    private List<String> lore;

    public FItemStack(Material material) {
        this.material = material;
        this.quantity = 1;
        this.lore = new ArrayList<>();
    }

    public FItemStack(Material material, int quantity) {
        this.material = material;
        this.quantity = quantity;
        this.lore = new ArrayList<>();
    }

    public FItemStack(Material material, int quantity, String displayName) {
        this.material = material;
        this.displayName = displayName;
        this.quantity = quantity;
        this.lore = new ArrayList<>();
    }

    public FItemStack setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public FItemStack setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public FItemStack setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public FItemStack addLoreLine(String text) {
        this.lore.add(text);
        return this;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(this.material, this.quantity);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (this.displayName != null && !this.displayName.isEmpty()) {
                meta.setDisplayName(this.displayName);
            }
            if (this.lore != null && !this.lore.isEmpty()) {
                meta.setLore(this.lore);
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}
