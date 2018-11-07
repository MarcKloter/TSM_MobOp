package mse.ch.tsm_mobop_app.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockContent {

    public static final List<CartItem> ITEMS = new ArrayList<CartItem>();

    public static final Map<String, CartItem> ITEM_MAP = new HashMap<String, CartItem>();

    static {
        // mock data
        addItem(new CartItem("1", "Betty Bossi Peanut Butter Jelly Sandwich", "Beschreibung", new BigDecimal(2.60), new BigDecimal(1.0)));
        addItem(new CartItem("2", "Strawberry Cake", "Beschreibung", new BigDecimal(4.65), new BigDecimal(1.56), "kg"));
        addItem(new CartItem("3", "Butterbrezel", "Beschreibung", new BigDecimal(1.90), new BigDecimal(1.0)));
        addItem(new CartItem("4", "Prix Garantie Choco Crisp Chocolate", "Beschreibung", new BigDecimal(0.60), new BigDecimal(1.0)));
        addItem(new CartItem("5", "Swiss Alpina Grün", "Beschreibung", new BigDecimal(0.90), new BigDecimal(4)));
        addItem(new CartItem("6", "Bauernbrot", "Beschreibung", new BigDecimal(2.30), new BigDecimal(1)));
        addItem(new CartItem("7", "Lindt Schokolade", "Beschreibung", new BigDecimal(6.25), new BigDecimal(250), "g"));
        addItem(new CartItem("8", "Rindsfilet", "Beschreibung", new BigDecimal(28.30), new BigDecimal(355), "g"));
        addItem(new CartItem("9", "Snickers", "Beschreibung", new BigDecimal(1.30), new BigDecimal(4)));
        addItem(new CartItem("10", "Bananen Max Havelar", "Beschreibung", new BigDecimal(2.30), new BigDecimal(0.784), "kg"));
        addItem(new CartItem("11", "Zucker 1kg", "Beschreibung", new BigDecimal(2.65), new BigDecimal(2)));
        addItem(new CartItem("12", "Barilla Spaghetti 1kg", "Beschreibung", new BigDecimal(4.35), new BigDecimal(1)));
    }

    private static void addItem(CartItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
