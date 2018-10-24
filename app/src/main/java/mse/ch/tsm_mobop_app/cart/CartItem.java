package mse.ch.tsm_mobop_app.cart;

public class CartItem {
    public final String id;
    public final String label;
    public final double price;
    public final double quantity;
    public final String quantity_label;

    public CartItem(String id, String label, double price, double quantity) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantity_label = "";
    }

    public CartItem(String id, String label, double price, double quantity, String quantity_label) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.quantity = quantity;
        this.quantity_label = quantity_label;
    }

    public int getQuantity() {
        return (quantity_label.equals("")) ? 1 : (int) quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return label;
    }
}
