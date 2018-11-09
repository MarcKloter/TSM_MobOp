package mse.ch.tsm_mobop_app.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mse.ch.tsm_mobop_app.R;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.CartItemViewHolder> {

    private final List<CartItem> cartContent;
    private final CartRecyclerViewListener crvListener;
    private final CartListener cListener;

    public CartRecyclerViewAdapter(CartRecyclerViewListener crvListener, CartListener cListener) {
        this.cartContent = new ArrayList<>();
        this.crvListener = crvListener;
        this.cListener = cListener;
    }

    public CartRecyclerViewAdapter(List<CartItem> items, CartRecyclerViewListener crvListener, CartListener cListener) {
        this.cartContent = items;
        this.crvListener = crvListener;
        this.cListener = cListener;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartItemViewHolder holder, int position) {
        holder.setItem(cartContent.get(position));
        holder.mQuantityView.setText(cartContent.get(position).getFormattedQuantity());
        holder.mLabelView.setText(cartContent.get(position).getLabel());
        holder.mPriceView.setText(cartContent.get(position).getFormattedPrice());

        crvListener.onCartContentChanged(getCartCount(), getTotal());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != cListener) {
                    cListener.onItemClick(holder.getItem());
                }
            }
        });
    }

    public int getItemPositionById(String uid) throws CartItemNotFoundException{
        CartItem searchItem = new CartItem(uid, "searchItem", "description", new BigDecimal(1), new BigDecimal(1));
        if(!cartContent.contains(searchItem)){
            throw new CartItemNotFoundException("Item not in cart!");
        }
        return cartContent.indexOf(searchItem);
    }

    public void increaseQuantityByPosition(int pos) {
        this.cartContent.get(pos).increaseItemCount();
        notifyDataSetChanged();
        crvListener.onCartContentChanged(getCartCount(), getTotal());
    }

    public void remove(int position) {
        CartItem item = cartContent.get(position);
        if (cartContent.contains(item)) {
            cartContent.remove(position);
            notifyItemRemoved(position);
            crvListener.onCartContentChanged(getCartCount(), getTotal());
        }
    }

    public void add(CartItem item){
        cartContent.add(item);
        notifyDataSetChanged();
        crvListener.onCartContentChanged(getCartCount(), getTotal());
    }

    public void updateItemQuantity(CartItem item) {
        int index = cartContent.indexOf(item);
        // remove the item if it's quantity was set to 0
        if(item.getQuantity().equals(BigDecimal.ZERO))
            cartContent.remove(index);
        else
            this.cartContent.get(index).setQuantity(item.getQuantity());

        notifyDataSetChanged();
        crvListener.onCartContentChanged(getCartCount(), getTotal());
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

    public void removeZeroQuantityEntries() {
        Iterator<CartItem> it = cartContent.iterator();
        while (it.hasNext()) {
            CartItem item = it.next();
            if(item.getQuantityLabel().isEmpty() && item.getQuantity().equals(BigDecimal.ZERO)) {
                it.remove();
                crvListener.onCartContentChanged(getCartCount(), getTotal());
            }
        }
    }

    public List<CartItem> getCartContent() {
        return cartContent;
    }

    @Override
    public int getItemCount() {
        return cartContent.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuantityView;
        public final TextView mLabelView;
        public final TextView mPriceView;
        private CartItem mItem;

        public CartItemViewHolder(View view) {
            super(view);
            mView = view;
            mQuantityView = view.findViewById(R.id.item_quantity);
            mLabelView =  view.findViewById(R.id.item_label);
            mPriceView = view.findViewById(R.id.item_price);
        }

        public CartItem getItem() {
            return mItem;
        }

        public void setItem(CartItem item) {
            mItem = item;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLabelView.getText() + "'";
        }
    }
}
