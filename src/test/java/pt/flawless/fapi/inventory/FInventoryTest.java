package pt.flawless.fapi.inventory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FInventoryTest {

    @Test
    void fitTitle_shortTitleIsUnchanged() {
        assertEquals("Loja", FInventory.fitTitle("Loja"));
    }

    @Test
    void fitTitle_exactlyTheLimitIsUnchanged() {
        String title = "a".repeat(FInventory.MAX_TITLE_LENGTH);
        assertEquals(title, FInventory.fitTitle(title));
    }

    @Test
    void fitTitle_nullFallsBackToTheDefault() {
        assertEquals("Menu", FInventory.fitTitle(null));
    }

    @Test
    void fitTitle_longTitleIsCutToTheLimit() {
        String cut = FInventory.fitTitle("b".repeat(40));
        assertEquals(FInventory.MAX_TITLE_LENGTH, cut.length());
    }

    @Test
    void fitTitle_neverEndsWithALoneSectionSign() {
        String title = "c".repeat(FInventory.MAX_TITLE_LENGTH - 1) + "§a" + "tail";
        String cut = FInventory.fitTitle(title);
        assertTrue(cut.length() <= FInventory.MAX_TITLE_LENGTH);
        assertTrue(!cut.endsWith("§"));
    }

    @Test
    void checkPosition_acceptsTheFirstAndLastSlot() {
        assertDoesNotThrow(() -> FInventory.checkPosition(0, 27));
        assertDoesNotThrow(() -> FInventory.checkPosition(26, 27));
    }

    @Test
    void checkPosition_rejectsOutOfRange() {
        IllegalArgumentException tooHigh = assertThrows(IllegalArgumentException.class, () -> FInventory.checkPosition(27, 27));
        assertTrue(tooHigh.getMessage().contains("27"));
        assertThrows(IllegalArgumentException.class, () -> FInventory.checkPosition(-1, 27));
    }
}
