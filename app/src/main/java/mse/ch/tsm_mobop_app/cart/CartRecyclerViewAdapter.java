package mse.ch.tsm_mobop_app.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mse.ch.tsm_mobop_app.R;
import mse.ch.tsm_mobop_app.cart.CartFragment.CartListener;

import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    private final List<CartItem> mValues;
    private final CartListener mListener;

    public CartRecyclerViewAdapter(List<CartItem> items, CartListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mQuantityView.setText(formatQuantity(mValues.get(position)));
        holder.mLabelView.setText(mValues.get(position).label);
        holder.mPriceView.setText(String.format("%.2f", mValues.get(position).price));

        mListener.onCartChanged(getQuantity(), getTotal());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemClick(holder.mItem);
                }
            }
        });
    }

    public void remove(int position) {
        CartItem item = mValues.get(position);
        if (mValues.contains(item)) {
            mValues.remove(position);
            notifyItemRemoved(position);
            mListener.onCartChanged(getQuantity(), getTotal());
        }
    }

    private String formatQuantity(CartItem item) {
        return String.format("%.2f", item.quantity) + item.quantity_label;
    }

    public int getQuantity() {
        int count = 0;
        for(CartItem item : mValues) {
            count += item.getQuantity();
        }
        return count;
    }

    public double getTotal() {
        double total = 0;
        for(CartItem item : mValues) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuantityView;
        public final TextView mLabelView;
        public final TextView mPriceView;
        public CartItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuantityView = (TextView) view.findViewById(R.id.item_quantity);
            mLabelView = (TextView) view.findViewById(R.id.item_label);
            mPriceView = (TextView) view.findViewById(R.id.item_price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLabelView.getText() + "'";
        }
    }
}
