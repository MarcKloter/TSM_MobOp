package mse.ch.tsm_mobop_app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.data.ArticleDataController;
import mse.ch.tsm_mobop_app.data.ArticleDataModel;
import mse.ch.tsm_mobop_app.data.OnDataReceivedEventListener;
import mse.ch.tsm_mobop_app.scan.ScanErrorFragment;
import mse.ch.tsm_mobop_app.scan.ScanLoadingFragment;

/**
 * This Activity handles the scan process with the QR Code Intent and the data fetching
 * (loading screen) from the database.
 */
public class ScanActivity extends AppCompatActivity implements OnDataReceivedEventListener, ScanErrorFragment.ScanErrorInteractionListener {

    private static final String INTENT_RETURN_EXTRA = "ARTICLE";
    private static final int REQUEST_CODE = 5234;
    private static final ScanErrorFragment SCAN_ERROR_FRAGMENT = new ScanErrorFragment();
    private static final ScanLoadingFragment SCAN_LOADING_FRAGMENT = new ScanLoadingFragment();

    private final ArticleDataController articleDataController = ArticleDataController.getInstance();
    private boolean changeFragmentToError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        this.setLoadingFragment();
        this.startQrCodeScanner();
    }

    protected void startQrCodeScanner(){
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result = intent.getStringExtra("SCAN_RESULT");
                this.loadDataFromId(result);
            } else if (resultCode == RESULT_CANCELED) {
                setResult(RESULT_CANCELED);
                //TODO: Delte follwing line
                this.loadDataFromId("2352");
                //finish();
            }
        }
    }

    protected void loadDataFromId(final String qrResult){
        try{
            int uid = Integer.parseInt(qrResult);
            articleDataController.getArticleById(uid, this);
        }
        catch (Exception ex){
            changeFragmentToError = true;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(changeFragmentToError){
            this.changeFragmentToError = false;
            this.setQrErrorFragment();
        }
    }

    @Override
    public void onDataReceived(Object object) {
        try{
            ArticleDataModel article = (ArticleDataModel)object;
            Intent result = new Intent();
            result.putExtra(INTENT_RETURN_EXTRA, article);
            setResult(RESULT_OK, result);
            finish();
        }
        catch (Exception ex){
            this.setQrErrorFragment();
        }
    }

    @Override
    public void onRetryButtonPress() {
        this.setLoadingFragment();
        this.startQrCodeScanner();
    }

    private void setLoadingFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.scan_fragment, SCAN_LOADING_FRAGMENT)
                .commit();
    }

    private void setQrErrorFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.scan_fragment, SCAN_ERROR_FRAGMENT)
                .commit();
    }
}
