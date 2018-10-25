package mse.ch.tsm_mobop_app.cart;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mse.ch.tsm_mobop_app.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    private final List<CartItem> cartContent;
    private final CartRecyclerViewListener crvListener;
    private final CartListener cListener;

    public CartRecyclerViewAdapter(List<CartItem> items, CartRecyclerViewListener crvListener, CartListener cListener) {
        Log.e("myapp", "SIZE: ---------" + items.size());
        this.cartContent = items;
        this.crvListener = crvListener;
        this.cListener = cListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = cartContent.get(position);
        holder.mQuantityView.setText(formatQuantity(cartContent.get(position)));
        holder.mLabelView.setText(cartContent.get(position).label);
        holder.mPriceView.setText(String.format("%.2f", cartContent.get(position).price));

        crvListener.onCartContentChanged(getCartCount(), getTotal());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != cListener) {
                    cListener.onItemClick(holder.mItem);
                }
            }
        });
    }

    public void remove(int position) {
        CartItem item = cartContent.get(position);
        if (cartContent.contains(item)) {
            cartContent.remove(position);
            notifyItemRemoved(position);
            crvListener.onCartContentChanged(getCartCount(), getTotal());
        }
    }

    private String formatQuantity(CartItem item) {
        double quantity = item.quantity.doubleValue();
        DecimalFormat format = new DecimalFormat("0.###");
        return format.format(quantity) + item.quantity_label;
    }

    public int getCartCount() {
        int count = 0;
        for(CartItem item : cartContent) {
            count += item.getItemCount();
        }
        return count;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        for(CartItem item : cartContent) {
            total = total.add(item.getPrice());
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return cartContent.size();
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
