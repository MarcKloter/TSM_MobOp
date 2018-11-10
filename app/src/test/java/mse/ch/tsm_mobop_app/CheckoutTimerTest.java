package mse.ch.tsm_mobop_app;

import org.junit.Assert;
import org.junit.Test;

import mse.ch.tsm_mobop_app.checkout.CheckoutTimer;
import mse.ch.tsm_mobop_app.checkout.CheckoutTimerListener;

public class CheckoutTimerTest implements CheckoutTimerListener {


    private final int TIME_TO_WAIT_MS = 10;
    private boolean callbackCalled = false;
    CheckoutTimer checkoutTimer = new CheckoutTimer();

    @Test
    public void testCallbackOfTimer() {
        this.checkoutTimer.startTimer(this, TIME_TO_WAIT_MS);
        try{
            Thread.sleep(TIME_TO_WAIT_MS*2);
        }
        catch (Exception ex){
            Assert.assertFalse(true);
        }
        if(this.callbackCalled){
            Assert.assertTrue(true);
        }else{
            Assert.assertFalse(true);
        }
    }

    @Override
    public void checkoutTimerExpiredEvent() {
        this.callbackCalled = true;
    }
}
