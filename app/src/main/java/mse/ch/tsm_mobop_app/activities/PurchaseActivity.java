package mse.ch.tsm_mobop_app.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartFragment;
import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.cart.CartListener;
import mse.ch.tsm_mobop_app.checkout.AskForCheckoutFragment;
import mse.ch.tsm_mobop_app.data.OrderArticleDataModel;
import mse.ch.tsm_mobop_app.data.OrderDataModelRecuded;
import mse.ch.tsm_mobop_app.details.DetailsFragment;
import mse.ch.tsm_mobop_app.data.ArticleDataModel;

public class PurchaseActivity extends AppCompatActivity implements CartListener, DetailsFragment.DetailsListener, AskForCheckoutFragment.AskForCheckoutFragmentListener {

    private static final String SCAN_INTENT_RETURN_EXTRA = "ARTICLE";
    private static final String CHECKOUT_INTENT_EXTRA = "ORDER";
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
        AskForCheckoutFragment newFragment = new AskForCheckoutFragment();
        newFragment.show(getSupportFragmentManager(), "checkout");
    }

    @Override
    public void onItemClick(CartItem item) {
        // TODO: handle landscape
        DetailsFragment details = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        details.setArguments(args);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.purchase_fragmet, details);
        fragmentTransaction.hide(CART_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAcceptButtonPress(CartItem item) {
        // return from details to cart
        getSupportFragmentManager().popBackStackImmediate();

        CART_FRAGMENT.updateItemQuantity(item);
    }

    @Override
    public void onScanButtonPress() {
        Intent intent = new Intent(PurchaseActivity.this, ScanActivity.class);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    private void setCartFragment() {
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

    private void handleScanResult(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            try {
                ArticleDataModel article = (ArticleDataModel) intent.getSerializableExtra(SCAN_INTENT_RETURN_EXTRA);
                CART_FRAGMENT.addOrIncreaseItemInCart(this.convertFromArticleDataModel(article));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private CartItem convertFromArticleDataModel(ArticleDataModel model) {
        return new CartItem("" + model.getUid(), model.getName(), model.getDescription(), new BigDecimal(model.getPricePerQty()), new BigDecimal(1), model.getQuantityType().getDesc());
    }

    /**
     * Method to proceed with the checkout if the user confirmed the chechout in the dialog.
     */
    @Override
    public void onProceedButtonClick() {
        Intent intent = new Intent(PurchaseActivity.this, CheckoutActivity.class);
        List<CartItem> items = this.CART_FRAGMENT.getAllItemsInCart();
        String user = getUniqueIMEIId(getBaseContext());

        List<OrderArticleDataModel> articlesForOrder = new ArrayList<>();
        for(CartItem current : items){
            int uid = Integer.parseInt(current.getId());
            articlesForOrder.add(new OrderArticleDataModel(uid, current.getQuantity().doubleValue()));
        }

        OrderDataModelRecuded order = new OrderDataModelRecuded(this.CART_FRAGMENT.getTotal().doubleValue(), user, "CREDIT-CARD", articlesForOrder);
        intent.putExtra(CHECKOUT_INTENT_EXTRA, order);
        startActivity(intent);
    }

    /**
     * This method was copied from stackoverflow just to retrieve the IMEI number of the phone,
     * to have something to identify the user.
     * https://stackoverflow.com/questions/48556566/best-way-to-get-device-imei-number-android-java-programmatically-with-onrequestp?noredirect=1
     * @param context
     * @return
     */
    private static String getUniqueIMEIId(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "RANDOM-USER " + new Random().nextInt(500000); //Changed that line
            }
            String imei = telephonyManager.getDeviceId();
            Log.e("imei", "=" + imei);
            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return android.os.Build.SERIAL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_found";
    }
}
