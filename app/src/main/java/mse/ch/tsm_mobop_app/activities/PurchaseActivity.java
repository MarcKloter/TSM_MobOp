package mse.ch.tsm_mobop_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartFragment;
import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.cart.MockContent;

public class PurchaseActivity extends AppCompatActivity implements CartFragment.CartListener {

    private TextView cartCounter;
    private TextView cartTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        cartCounter = findViewById(R.id.cart_count);
        cartTotal = findViewById(R.id.cart_total);
        setQuantity(0);
        setTotal(0);
    }

    @Override
    public void onItemClick(CartItem item) {

    }

    @Override
    public void onCartChanged(int quantity, double total) {
        setQuantity(quantity);
        setTotal(total);
    }

    private void setQuantity(int quantity) {
        cartCounter.setText(String.format("%d %s in your cart", quantity, (quantity != 1) ? "items" : "item"));
    }

    private void setTotal(double price) {
        cartTotal.setText(String.format("%.2f CHF", price));
    }
}
