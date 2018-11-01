package mse.ch.tsm_mobop_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.math.BigDecimal;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartFragment;
import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.cart.CartListener;
import mse.ch.tsm_mobop_app.data.ArticleDataModel;

public class PurchaseActivity extends AppCompatActivity implements CartListener {

    private static final String INTENT_RETURN_EXTRA = "ARTICLE";
    private static final int SCAN_REQUEST_CODE = 21435;
    private static final CartFragment CART_FRAGMENT = new CartFragment(); //This class holds the cart fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        this.setCartFragment();
    }

    @Override
    public void onProceedToCheckout() {
        Intent intent = new Intent(PurchaseActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(CartItem item) {
        // TODO: open item details fragment
    }
    @Override
    public void onScanButtonPress() {
        Intent intent = new Intent(PurchaseActivity.this, ScanActivity.class);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    private void setCartFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.purchase_fragmet, CART_FRAGMENT)
                .commit();
    }

    //This method needs to handle all possible results. To add a new one, add a request code
    //to the class and a if-statement in this method.
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SCAN_REQUEST_CODE) {
            this.handleScanResult(resultCode, intent);
        }
    }

    private void handleScanResult(int resultCode, Intent intent){
        if(resultCode == RESULT_OK){
            try{
                ArticleDataModel article = (ArticleDataModel)intent.getSerializableExtra(INTENT_RETURN_EXTRA);
                CART_FRAGMENT.addOrIncreaseItemInCart(this.convertFromArticleDataModel(article));
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private CartItem convertFromArticleDataModel(ArticleDataModel model){
        return new CartItem("" + model.getUid(), model.getName(), new BigDecimal(model.getPricePerQty()), new BigDecimal(1), model.getQuantityType().toString());
    }
}
