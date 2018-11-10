package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.scan.ScanErrorFragment;

public class ScanErrorInteractionListenerTest implements ScanErrorFragment.ScanErrorInteractionListener {
    private boolean callbackCalled = false;

    @Test
    public void testCallbackFragmentListener(){
        ScanErrorFragment fragment = new ScanErrorFragment();
        fragment.attachManual(this);
        fragment.handleRetryButtonClicked();
        Assert.assertTrue(this.callbackCalled);
    }

    @Override
    public void onRetryButtonPress() {
        this.callbackCalled = true;
    }
}
