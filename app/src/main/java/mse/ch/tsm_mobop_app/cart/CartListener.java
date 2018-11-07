package mse.ch.tsm_mobop_app.cart;

public interface CartListener {
    void onProceedToCheckout();
    void onItemClick(CartItem item);
    void onScanButtonPress();
}
