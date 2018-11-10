package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.checkout.AskForCheckoutFragment;

public class AskForCheckoutFragmentListenerTest implements AskForCheckoutFragment.AskForCheckoutFragmentListener {

    private boolean callbackCalled = false;

    @Test
    public void testCallbackFragmentListener(){
        AskForCheckoutFragment fragment = new AskForCheckoutFragment();
        fragment.attachManual(this);
        fragment.handleProceedButtonClicked();
        try{
            Thread.sleep(10);
        }
        catch (Exception ex){
            Assert.assertFalse(true);
        }
        Assert.assertTrue(this.callbackCalled);
    }

    @Override
    public void onProceedButtonClick() {
        this.callbackCalled = true;
    }
}
