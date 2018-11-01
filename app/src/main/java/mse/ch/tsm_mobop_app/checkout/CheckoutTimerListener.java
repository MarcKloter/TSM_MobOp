package mse.ch.tsm_mobop_app.checkout;

/**
 * This interface supports a callback from the CheckoutTimer class
 */
public interface CheckoutTimerListener {
    /**
     * This is the callback-method of the class CheckoutTimer. This method is called when the
     * invoked timer of the CheckoutTimer is expired.
     */
    void checkoutTimerExpiredEvent();
}
