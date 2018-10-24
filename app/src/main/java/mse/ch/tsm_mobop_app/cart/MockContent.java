package mse.ch.tsm_mobop_app.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockContent {

    public static final List<CartItem> ITEMS = new ArrayList<CartItem>();

    public static final Map<String, CartItem> ITEM_MAP = new HashMap<String, CartItem>();

    private static final int COUNT = 25;

    static {
        // mock data
        addItem(new CartItem("1", "Betty Bossi Peanut Butter Jelly Sandwich", 2.60, 1.0));
        addItem(new CartItem("2", "Strawberry Cake", 4.65, 1.56, "kg"));
    }

    private static void addItem(CartItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
