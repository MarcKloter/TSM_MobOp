package mse.ch.tsm_mobop_app.cart;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CartItem {
    private final String id;
    private final String label;
    private final BigDecimal price;
    private final BigDecimal quantity;
    private final String quantityLabel;

    public CartItem(String id, String label, BigDecimal price, BigDecimal quantity) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantityLabel = "";
    }

    public CartItem(String id, String label, BigDecimal price, BigDecimal quantity, String quantity_label) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantityLabel = quantity_label;
    }

    public int getItemCount() {
        return quantityLabel.isEmpty() ? quantity.intValueExact() : 1;
    }

    public BigDecimal getPrice() {
        return quantityLabel.isEmpty() ? price.multiply(quantity) : price;
    }

    public String getLabel() {
        return label;
    }

    public String getFormattedPrice() {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(getPrice());
    }

    public String getFormattedQuantity() {
        double d = quantity.doubleValue();
        DecimalFormat format = new DecimalFormat("0.###");
        return format.format(d) + quantityLabel;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return label;
    }
}
