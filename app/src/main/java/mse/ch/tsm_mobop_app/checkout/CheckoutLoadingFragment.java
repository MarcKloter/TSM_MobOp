package mse.ch.tsm_mobop_app.checkout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mse.ch.tsm_mobop_app.R;

/**
 * Fragment to display the payment process and with it the progress indicator
 */
public class CheckoutLoadingFragment extends Fragment {

    public CheckoutLoadingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout_loading, container, false);
    }
}
