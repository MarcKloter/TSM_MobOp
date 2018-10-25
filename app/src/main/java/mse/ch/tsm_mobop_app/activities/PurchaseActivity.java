package mse.ch.tsm_mobop_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.cart.CartListener;

public class PurchaseActivity extends AppCompatActivity implements CartListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }

    @Override
    public void onProceedToCheckout() {
        // TODO: open checkout fragment
    }

    @Override
    public void onItemClick(CartItem item) {
        // TODO: open item details fragment
    }
    @Override
    public void onScanButtonPress() {
        // TODO: fire QR scan intent
    }
}
