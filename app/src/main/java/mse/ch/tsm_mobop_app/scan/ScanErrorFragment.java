package mse.ch.tsm_mobop_app.scan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import mse.ch.tsm_mobop_app.R;

/**
 * Fragment to display an error, if the QR scanning was not successful.
 */
public class ScanErrorFragment extends Fragment {

    private ScanErrorInteractionListener mListener;

    public ScanErrorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan_error, container, false);

        Button retryBut = (Button) view.findViewById(R.id.error_retry);
        retryBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRetryButtonPress();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.onAttach(context);

        if (context instanceof ScanErrorInteractionListener) {
            mListener = (ScanErrorInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ScanErrorInteractionListener");
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
     public interface ScanErrorInteractionListener {
         void onRetryButtonPress();
     }
}
