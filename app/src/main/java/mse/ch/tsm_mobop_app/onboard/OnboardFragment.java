package mse.ch.tsm_mobop_app.onboard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mse.ch.tsm_mobop_app.R;


/**
 * Onboard fragment for the user to engage with the first action
 */
public class OnboardFragment extends Fragment {

    private OnboardInteractionListener mListener;

    public OnboardFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of the OnboardFragment
     */
    public static OnboardFragment newInstance() {
        return new OnboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboard, container, false);

        FloatingActionButton scanFab = view.findViewById(R.id.onboard_scan);
        scanFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onScanButtonPress();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnboardInteractionListener) {
            mListener = (OnboardInteractionListener) context;
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
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnboardInteractionListener {
        void onScanButtonPress();
    }
}
