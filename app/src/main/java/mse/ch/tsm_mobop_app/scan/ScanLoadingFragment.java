package mse.ch.tsm_mobop_app.scan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mse.ch.tsm_mobop_app.R;

/**
 * Fragment to show the progress animation, that the data is loading.
 */

public class ScanLoadingFragment extends Fragment {

    public ScanLoadingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_loading, container, false);
    }

}
