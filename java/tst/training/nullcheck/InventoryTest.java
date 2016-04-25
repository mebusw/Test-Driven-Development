package training.nullcheck;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class InventoryTest {

    /* inventory.itemForBarcode("2", |item| sum+=add(item));
     */

    @Test
    public void existingItem() {
        ArrayList<Item> items = new ArrayList<>();
        Inventory inventory = new Inventory();

        Item item = inventory.itemForBarcodeFn("1", (x) -> items.add(x));

        assertNotNull(item);
        assertEquals(1, items.size());
    }

    @Test
    public void missingItem() {
        ArrayList<Item> items = new ArrayList<>();
        Inventory inventory = new Inventory();

        Item item = inventory.itemForBarcodeFn("2", (x) -> items.add(x));


        assertNull(item);
        assertEquals(0, items.size());

    }


}