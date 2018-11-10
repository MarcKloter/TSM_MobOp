package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.onboard.OnboardFragment;

public class OnboardInteractionListenerTest implements OnboardFragment.OnboardInteractionListener {
    private boolean callbackCalled = false;

    @Test
    public void testCallbackFragmentListener(){
        OnboardFragment fragment = new OnboardFragment();
        fragment.attachManual(this);
        fragment.handlePlusButtonClicked();
        Assert.assertTrue(this.callbackCalled);
    }

    @Override
    public void onScanButtonPress() {
        this.callbackCalled = true;
    }
}
