package mse.ch.tsm_mobop_app.cart;

import android.util.Log;

import java.math.BigDecimal;

public class CartItem {
    public final String id;
    public final String label;
    public final BigDecimal price;
    public final BigDecimal quantity;
    public final String quantity_label;

    public CartItem(String id, String label, BigDecimal price, BigDecimal quantity) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantity_label = "";
    }

    public CartItem(String id, String label, BigDecimal price, BigDecimal quantity, String quantity_label) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantity_label = quantity_label;
    }

    public int getItemCount() {
        return (quantity_label.isEmpty()) ? quantity.intValueExact() : 1;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return label;
    }
}
