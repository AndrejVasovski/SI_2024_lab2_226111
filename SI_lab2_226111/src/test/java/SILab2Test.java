import mk.ukim.finki.SILab2;
import mk.ukim.finki.SILab2.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void testAllItemsNull() {
        Throwable exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertEquals("allItems list can't be null!", exception.getMessage());
    }

    @Test
    void testEmptyListWithSufficientPayment() {
        List<Item> items = new ArrayList<>();
        assertTrue(SILab2.checkCart(items, 100));
    }

    @Test
    void testListItemWithInsufficientPayment() {
        Item testItem1 = new Item(null, "123", 100, 0);
        Item testItem2 = new Item("ItemName", "321", 50, 0);
        List<Item> items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);
        assertFalse(SILab2.checkCart(items, 10));
    }

    @Test
    void testListItemWithNoBarcode() {
        Item testItem1 = new Item(null, null, 100, 0);
        Item testItem2 = new Item("ItemName", "321", 50, 0);
        List<Item> items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);

        Throwable exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, 10));
        assertEquals("No barcode!", exception.getMessage());
    }

    @Test
    void testListItemWithInvalidCharacter() {
        Item testItem1 = new Item(null, "123", 100, 0);
        Item testItem2 = new Item("ItemName", "1asd", 50, 0);
        List<Item> items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);

        Throwable exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, 10));
        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    void testListItemsWithNameTestWithDiscountTestWithAndSpecialDiscountTest() {
        Item testItem1 = new Item(null, "123", 100, 0);
        Item testItem2 = new Item("ItemName", "321", 50, 10);
        Item testItem3 = new Item("ItemName", "0321", 350, 10);
        List<Item> items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);
        items.add(testItem3);
        assertFalse(SILab2.checkCart(items, 10));
    }

    @Test
    void testListItemsMultipleConditionCriteria() {
        Item testItem1 = new Item(null, "123", 100, 0);
        Item testItem2 = new Item("ItemName2", "321", 350, 0);
        Item testItem3 = new Item("ItemName3", "321", 350, 10);
        Item testItem4 = new Item("ItemName4", "0123", 350, 10);
        List<Item> items = new ArrayList<>();
        items.add(testItem1);
        items.add(testItem2);
        items.add(testItem3);
        items.add(testItem4);
        assertTrue(SILab2.checkCart(items, 10000));
    }
}
