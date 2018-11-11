package mse.ch.tsm_mobop_app.details;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.math.BigDecimal;
import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartItem;


public class DetailsFragment extends Fragment {
    private DetailsListener dListener;

    private CartItem item;

    private TextView quantityText;
    private TextView quantityLabel;
    private ImageButton quantityDecrease;
    private ImageButton quantityIncrease;
    private TextView amountText;
    private TextView amountLabel;

    private int newQuantity;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        this.item = (CartItem) getArguments().getSerializable("item");

        TextView itemLabel = view.findViewById(R.id.item_label);
        itemLabel.setText(this.item.getLabel());

        TextView itemPrice = view.findViewById(R.id.item_price);
        itemPrice.setText(String.format("CHF %s", this.item.getFormattedBasePrice()));

        TextView itemDescription = view.findViewById(R.id.item_description);
        itemDescription.setText(this.item.getDescription());

        this.quantityText = view.findViewById(R.id.detail_quantity_text);
        this.quantityLabel = view.findViewById(R.id.detail_quantity);
        this.quantityDecrease = view.findViewById(R.id.detail_quantity_minus);
        this.quantityIncrease = view.findViewById(R.id.detail_quantity_plus);

        this.amountText = view.findViewById(R.id.detail_amount_text);
        this.amountLabel = view.findViewById(R.id.detail_amount);
        amountLabel.setText(item.getFormattedQuantity());

        if(item.getQuantityLabel().isEmpty()) {
            setQuantityInformation();
            setupControlButtons();
        }

        showQuantityInformation();
        setupFAB(view);

        return view;
    }

    private void setQuantityInformation() {
        this.newQuantity = item.getQuantity().intValueExact();
        updateQuantityLabel();
    }

    private void showQuantityInformation() {
        if(item.getQuantityLabel().isEmpty()) {
            quantityText.setVisibility(TextView.VISIBLE);
            quantityLabel.setVisibility(TextView.VISIBLE);
            quantityDecrease.setVisibility(ImageButton.VISIBLE);
            quantityIncrease.setVisibility(ImageButton.VISIBLE);

            amountText.setVisibility(TextView.GONE);
            amountLabel.setVisibility(TextView.GONE);
        } else {
            amountText.setVisibility(TextView.VISIBLE);
            amountLabel.setVisibility(TextView.VISIBLE);

            quantityText.setVisibility(TextView.GONE);
            quantityLabel.setVisibility(TextView.GONE);
            quantityDecrease.setVisibility(ImageButton.GONE);
            quantityIncrease.setVisibility(ImageButton.GONE);
        }
    }

    private void setupControlButtons() {
        quantityDecrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(newQuantity > 0) {
                    newQuantity--;
                    updateQuantityLabel();
                }
            }
        });

        quantityIncrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newQuantity++;
                updateQuantityLabel();
            }
        });
    }

    private void updateQuantityLabel() {
        String val = Integer.toString(newQuantity);
        quantityLabel.setText(val);
    }

    private void setupFAB(View view) {
        FloatingActionButton acceptFab = view.findViewById(R.id.detail_accept);
        acceptFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(item.getQuantityLabel().isEmpty())
                    item.setQuantity(new BigDecimal(newQuantity));
                handleAcceptButtonClicked();
            }
        });
    }

    public void handleAcceptButtonClicked(){
        if (dListener != null) {
            dListener.onAcceptButtonPress(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailsListener) {
            dListener = (DetailsListener) context;
        } else {
            //do nothing. Would need to create new error handling with own exception
        }
    }
    public void attachManual(DetailsListener listener){
        dListener =  listener;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        dListener = null;
    }

    public interface DetailsListener {
        void onAcceptButtonPress(CartItem item);
    }
}
