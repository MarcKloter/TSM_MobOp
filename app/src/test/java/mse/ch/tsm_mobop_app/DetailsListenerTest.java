package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.details.DetailsFragment;

public class DetailsListenerTest implements DetailsFragment.DetailsListener {
    private boolean callbackCalled = false;

    @Test
    public void testCallbackFragmentListener(){
        DetailsFragment fragment = new DetailsFragment();
        fragment.attachManual(this);
        fragment.handleAcceptButtonClicked();
        Assert.assertTrue(this.callbackCalled);
    }


    @Override
    public void onAcceptButtonPress(CartItem item) {
        this.callbackCalled = true;
    }
}
