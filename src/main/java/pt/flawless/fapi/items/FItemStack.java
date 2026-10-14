package pt.flawless.fapi.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class FItemStack {
    private Material material;
    private String displayName;
    private int quantity;
    private short durability;
    private List<String> lore;
    private MaterialData materialData;

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

    public FItemStack setDurability(short durability) {
        this.durability = durability;
        return this;
    }

    public int getDurability() {
        return this.durability;
    }

    public FItemStack setMaterialData(MaterialData materialData) {
        this.materialData = materialData;
        return this;
    }

    public MaterialData getMaterialData() {
        return this.materialData;
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(this.material, this.quantity);
        ItemMeta meta = item.getItemMeta();

        item.setDurability(this.durability);

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
