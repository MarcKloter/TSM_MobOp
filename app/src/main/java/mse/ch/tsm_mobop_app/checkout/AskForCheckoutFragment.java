package mse.ch.tsm_mobop_app.checkout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mse.ch.tsm_mobop_app.R;

/**
 * This Fragment is to display the question if you really want to checkout or not.
 */
public class AskForCheckoutFragment extends DialogFragment {

    private AskForCheckoutFragmentListener mListener;

    public AskForCheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.checkout_ask_title)
                .setPositiveButton(R.string.checkout_ask_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onProceedButtonClick();
                    }
                })
                .setNegativeButton(R.string.checkout_ask_no, null);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask_for_checkout, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AskForCheckoutFragmentListener) {
            mListener = (AskForCheckoutFragmentListener) context;
        } else {
            //do nothing. Would need to create new error handling with own exception
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface describes a method which shall handle the process to do when the user
     * accepted this dialog and confirmed to proceed with the checkout.
     */
    public interface AskForCheckoutFragmentListener {
        void onProceedButtonClick();
    }
}
