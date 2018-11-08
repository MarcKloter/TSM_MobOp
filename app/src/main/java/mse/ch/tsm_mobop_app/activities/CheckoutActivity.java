package mse.ch.tsm_mobop_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.checkout.CheckoutLoadingFragment;
import mse.ch.tsm_mobop_app.checkout.CheckoutTimer;
import mse.ch.tsm_mobop_app.checkout.CheckoutTimerListener;
import mse.ch.tsm_mobop_app.checkout.CheckoutSuccessfulFragment;

/**
 * This Activity handles the checkout process and the final of the app flow.
 */
public class CheckoutActivity extends AppCompatActivity implements CheckoutSuccessfulFragment.OnFragmentInteractionListener, CheckoutTimerListener {

    private static final CheckoutLoadingFragment CHECKOUT_LOADING_FRAGMENT = new CheckoutLoadingFragment();
    private static final CheckoutSuccessfulFragment CHECKOUT_SUCCESSFUL_FRAGMENT = new CheckoutSuccessfulFragment();
    private CheckoutTimer checkoutTimer = new CheckoutTimer();
    private CheckoutState lastState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        this.setCheckoutLoadingFragment();
        this.lastState = CheckoutState.CHECKOUT_STARTED;
        this.handleState();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    /**
     * State-machine for the different states while the checkout is ongoing.
     */
    private void handleState(){
        switch(this.lastState){

            case CHECKOUT_STARTED:
                this.checkoutTimer.startTimer(this);
                this.lastState = CheckoutState.CHECKOUT_ONGOING;
                break;
            case CHECKOUT_ONGOING:
                this.setCheckoutSuccessfulFragment();
                this.checkoutTimer.startTimer(this);
                this.lastState = CheckoutState.CHECKOUT_DONE;
                break;
            case CHECKOUT_DONE:
                this.lastState = CheckoutState.APP_RESTARTED;
                this.restartAppFlow();
                break;
            case APP_RESTARTED:
            default:
                break;
        }
    }

    /**
     * Method to set the loading fragment as the active fragment
     */
    private void setCheckoutLoadingFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.checkout_fragment, CHECKOUT_LOADING_FRAGMENT)
                .commit();
    }

    /**
     * Method to set the successful fragment as the active fragment
     */
    private void setCheckoutSuccessfulFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.checkout_fragment, CHECKOUT_SUCCESSFUL_FRAGMENT)
                .commit();
    }

    /**
     * This method is called when the user touches the last fragment "payment successful".
     */
    @Override
    public void onFragmentTouch() {
        this.handleState();
    }

    /**
     * This method restarts the whole app and resets the history (you are not able to go back then).
     */
    private void restartAppFlow(){
        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName() );

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
    }

    /**
     * This method is called when the invoked timer of the CheckoutTimer is expired.
     */
    @Override
    public void checkoutTimerExpiredEvent() {
        this.handleState();
    }

    /**
     * Different state for the local state machine.
     */
    private enum CheckoutState {
        CHECKOUT_STARTED,
        CHECKOUT_ONGOING,
        CHECKOUT_DONE,
        APP_RESTARTED
    }
}