package mse.ch.tsm_mobop_app.cart;

import java.math.BigDecimal;

public interface CartRecyclerViewListener {
    void onCartContentChanged(int cartCount, BigDecimal total);
}
