package mse.ch.tsm_mobop_app.checkout;

/**
 * The CheckoutTimer can be used to simulate the payment process (doing something in the
 * background for a few seconds) or to delay the procedure and display the success message.
 */
public class CheckoutTimer {
    private static final int CHECKOUT_TIMER_TIMOUT_MS = 4000;
    private CheckoutTimerListener checkoutTimerListener;

    public void startTimer(CheckoutTimerListener checkoutTimerListener){
        this.checkoutTimerListener = checkoutTimerListener;
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(CHECKOUT_TIMER_TIMOUT_MS);
                }
                catch (InterruptedException ex){
                    //Sleeping thread was interrupted. No problem.
                    Thread.currentThread().interrupt();
                }
                finally {
                    notifyTimerExpired();
                }
            }
        };
        thread.start();
    }

    private void notifyTimerExpired(){
        if(this.checkoutTimerListener != null){
            this.checkoutTimerListener.checkoutTimerExpiredEvent();
        }
    }
}

