package training.nullcheck;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

class Item {
    private String name;
    private int price;
    public static Item NONE = new Item("Item not found", 100);

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

public class Inventory {
    Map<String, Item> items = new LinkedHashMap();

    public Inventory() {
        Item milk = new Item("Milk", 7000);
        items.put("1", new Item("Preserved Duck Eggs", 150000));
        items.put("3", milk);
    }

    //为Inventory补一个测试，查询不存在的item。
    public Item itemForBarcode(String barcode) {
        Item item = items.get(barcode);
        if (item == null)
            item = Item.NONE;
        return item;
    }


    public Item itemForBarcodeLambda(String barcode, Action<Item> action) {
        Item item = items.get(barcode);
        if (item != null) {
            action.tell(item);
        }
        return item;
    }

    interface Action<T> {
        public void tell(final T t);
    }


    public Item itemForBarcodeFn(String barcode, Consumer<Item> fn) {
        Item t = items.get(barcode);
        if (t != null)
            fn.accept(t);
        return t;
    }


}
