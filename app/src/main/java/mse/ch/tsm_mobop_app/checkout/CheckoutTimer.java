package mse.ch.tsm_mobop_app.checkout;

/**
 * The CheckoutTimer can be used to simulate the payment process (doing something in the
 * background for a few seconds) or to delay the procedure and display the success message.
 */
public class CheckoutTimer {
    private CheckoutTimerListener checkoutTimerListener;

    public void startTimer(CheckoutTimerListener checkoutTimerListener, final int timeToWait){
        this.checkoutTimerListener = checkoutTimerListener;
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(timeToWait);
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

