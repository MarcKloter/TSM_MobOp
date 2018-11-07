package mse.ch.tsm_mobop_app.cart;

import android.util.Log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class CartItem implements Serializable {
    //TODO: Refactor this model class
    private final String id;
    private final String label;
    private final BigDecimal price;
    private BigDecimal quantity;
    private final String quantityLabel;
    private final String description;

    public CartItem(String id, String label, String description, BigDecimal price, BigDecimal quantity) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.quantityLabel = "";
    }

    public CartItem(String id, String label, String description, BigDecimal price, BigDecimal quantity, String quantity_label) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.quantityLabel = quantity_label;
    }

    public int getItemCount() {
        return quantityLabel.isEmpty() ? quantity.intValueExact() : 1;
    }

    public void increaseItemCount(){
        this.quantity = this.quantity.add(new BigDecimal(1));
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

    public String getFormattedBasePrice() {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(price);
    }

    public String getFormattedQuantity() {
        double d = quantity.doubleValue();
        DecimalFormat format = new DecimalFormat("0.###");
        return format.format(d) + quantityLabel;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantityLabel() {
        return quantityLabel;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getId(), cartItem.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
