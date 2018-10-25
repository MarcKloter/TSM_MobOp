package mse.ch.tsm_mobop_app.cart;

import java.math.BigDecimal;

public interface CartListener {
    void onProceedToCheckout();
    void onItemClick(CartItem item);
    void onScanButtonPress();
}
