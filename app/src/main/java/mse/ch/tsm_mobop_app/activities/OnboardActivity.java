package mse.ch.tsm_mobop_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mse.ch.tsm_mobop_app.onboard.OnboardFragment.OnboardInteractionListener;
import mse.ch.tsm_mobop_app.R;

public class OnboardActivity extends AppCompatActivity implements OnboardInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
    }

    @Override
    public void onScanButtonPress() {
        Intent intent = new Intent(OnboardActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }
}
