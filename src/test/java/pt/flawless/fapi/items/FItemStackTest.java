package pt.flawless.fapi.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.junit.jupiter.api.Test;

class FItemStackTest {

    @Test
    void durabilityRoundTripsAsAShort() {
        short durability = new FItemStack(Material.WOOL).setDurability((short) 5).getDurability();
        assertEquals((short) 5, durability);
    }

    @Test
    void setMaterialDataSetsTheDurability() {
        FItemStack item = new FItemStack(Material.WOOL).setMaterialData(new MaterialData(Material.WOOL, (byte) 14));
        assertEquals((short) 14, item.getDurability());
    }

    @Test
    void setMaterialDataNullKeepsTheDurability() {
        FItemStack item = new FItemStack(Material.WOOL).setDurability((short) 3).setMaterialData(null);
        assertEquals((short) 3, item.getDurability());
    }
}
