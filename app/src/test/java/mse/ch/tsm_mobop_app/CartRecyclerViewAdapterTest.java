package mse.ch.tsm_mobop_app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mse.ch.tsm_mobop_app.cart.CartItem;
import mse.ch.tsm_mobop_app.cart.CartItemNotFoundException;
import mse.ch.tsm_mobop_app.cart.CartListener;
import mse.ch.tsm_mobop_app.cart.CartRecyclerViewAdapter;
import mse.ch.tsm_mobop_app.cart.CartRecyclerViewListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CartRecyclerViewAdapterTest {
    @Mock
    private CartRecyclerViewListener crvListener;

    @Mock
    private CartListener cListener;

    private CartRecyclerViewAdapter crva;

    private final CartItem item1 = new CartItem("7283", "Test Item 1", "Test Item 1 Description", BigDecimal.valueOf(2.20), new BigDecimal(2));
    private final CartItem item2 = new CartItem("8721", "Test Item 2", "Test Item 2 Description", BigDecimal.valueOf(5.25), new BigDecimal(1));
    private final CartItem item3 = new CartItem("4532", "Test Item 3", "Test Item 3 Description", BigDecimal.valueOf(1.00), new BigDecimal(5));

    @Before
    public void setup() {
        List<CartItem> content = new ArrayList<>();
        content.add(item1);
        content.add(item2);
        content.add(item3);
        crva = new CartRecyclerViewAdapter(crvListener, cListener, content);
    }

    @Test
    public void getCartCount_isCorrect() {
        assertEquals(8, crva.getCartCount());
    }

    @Test
    public void getItemCount_isCorrect() {
        assertEquals(3, crva.getItemCount());
    }

    @Test
    public void getItemPositionById_isCorrect() {
        try {
            assertEquals(1, crva.getItemPositionById("8721"));
        } catch (CartItemNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getTotal_isCorrect() {
        assertEquals(BigDecimal.valueOf(14.65), crva.getTotal());
    }
}