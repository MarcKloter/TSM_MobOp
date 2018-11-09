package mse.ch.tsm_mobop_app.cart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.List;
import mse.ch.tsm_mobop_app.R;

/**
 * Fragment representing the shopping cart
 */
public class CartFragment extends Fragment implements CartRecyclerViewListener {
    private CartListener cListener;

    private TextView cartCounter;
    private TextView cartTotal;

    private FloatingActionButton checkoutFAB;

    private CartRecyclerViewAdapter cartRecyclerViewAdapter;
    private LinearLayout cartEmptyView;
    private LinearLayout cart;

    /**
     * Mandatory empty constructor for the fragment manager
     */
    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        //Initialisation of button actions
        FloatingActionButton scanFab = view.findViewById(R.id.cart_scan);
        scanFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (cListener != null) {
                    cListener.onScanButtonPress();
                }
            }
        });

        //Initialisation of attributes
        this.cartCounter = view.findViewById(R.id.cart_count);
        this.cartTotal = view.findViewById(R.id.cart_total);
        this.checkoutFAB = view.findViewById(R.id.cart_checkout);
        RecyclerView cartRecyclerView = view.findViewById(R.id.cart_recycler_view);
        this.cartEmptyView = view.findViewById(R.id.cart_empty_view);
        this.cart = view.findViewById(R.id.cart);
        this.cartRecyclerViewAdapter = new CartRecyclerViewAdapter(this, cListener);
        this.setupRecyclerView(cartRecyclerView);

        checkoutFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (cListener != null) {
                    cListener.onProceedToCheckout();
                }
            }
        });

        return view;
    }

    private void setupRecyclerView(View view) {
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(this.cartRecyclerViewAdapter);

            // add divider
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            setUpItemTouchHelper(recyclerView);
        }
    }

    /**
     * swipe to delete
     * From https://github.com/nemanja-kovacevic/recycler-view-swipe-to-delete/
     */
    private void setUpItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;
            RecyclerView recyclerView;

            private void init(RecyclerView recyclerView) {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) CartFragment.this.getResources().getDimension(R.dimen.clear_margin);
                initiated = true;
                this.recyclerView = recyclerView;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                cartRecyclerViewAdapter.remove(swipedPosition);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init(recyclerView);
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onCartContentChanged(int itemCount, BigDecimal total) {
        setCartCount(itemCount);
        setTotal(total);

        // toggle empty state
        adjustVisibility();
    }

    private void adjustVisibility() {
        if(cartRecyclerViewAdapter.getCartCount() == 0) {
            checkoutFAB.hide();
            cart.setVisibility(LinearLayout.GONE);
            cartEmptyView.setVisibility(LinearLayout.VISIBLE);
        } else {
            checkoutFAB.show();
            cart.setVisibility(LinearLayout.VISIBLE);
            cartEmptyView.setVisibility(LinearLayout.GONE);
        }
    }

    private void setCartCount(int cartCount) {
        cartCounter.setText(String.format("%d %s in your cart", cartCount, (cartCount != 1) ? "items" : "item"));
    }

    private void setTotal(BigDecimal total) {
        cartTotal.setText(String.format("%.2f CHF", total));
    }

    public void addOrIncreaseItemInCart(CartItem item){
        try{
            int existingPos = this.cartRecyclerViewAdapter.getItemPositionById(item.getId());
            this.cartRecyclerViewAdapter.increaseQuantityByPosition(existingPos);
        }
        catch (CartItemNotFoundException ex){
            this.cartRecyclerViewAdapter.add(item);
        }
    }

    public void updateItemQuantity(CartItem item) {
        this.cartRecyclerViewAdapter.updateItemQuantity(item);
    }

    public List<CartItem> getAllItemsInCart(){
        return this.cartRecyclerViewAdapter.getCartContent();
    }

    public BigDecimal getTotal(){
        return this.cartRecyclerViewAdapter.getTotal();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CartListener) {
            cListener = (CartListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cListener = null;
    }
}
