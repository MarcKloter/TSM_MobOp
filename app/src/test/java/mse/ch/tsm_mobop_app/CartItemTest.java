package mse.ch.tsm_mobop_app;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import mse.ch.tsm_mobop_app.cart.CartItem;

import static org.junit.Assert.*;

public class CartItemTest {
    private final String id = "5215";
    private final String label = "Test Item";
    private final BigDecimal price = new BigDecimal(4.50);
    private BigDecimal quantity = new BigDecimal(3);
    private final String quantityLabel = "kg";
    private final String description = "A simple test item.";

    private CartItem cartItem_unlabeled;
    private CartItem cartItem_labeled;

    @Before
    public void setup() {
        cartItem_unlabeled = new CartItem(id, label, description, price, quantity);
        cartItem_labeled = new CartItem(id, label, description, price, quantity, quantityLabel);
    }

    @Test
    public void fiveParamConstructor_isCorrect() {
        CartItem item = new CartItem(id, label, description, price, quantity);
        assertEquals(id, item.getId());
        assertEquals(label, item.getLabel());
        assertEquals(description, item.getDescription());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void sixParamConstructor_isCorrect() {
        CartItem item = new CartItem(id, label, description, price, quantity, quantityLabel);
        assertEquals(id, item.getId());
        assertEquals(label, item.getLabel());
        assertEquals(description, item.getDescription());
        assertEquals(quantity, item.getQuantity());
        assertEquals(quantityLabel, item.getQuantityLabel());
    }

    @Test
    public void getItemCount_isCorrect() {
        // this is one item a given quantity, so one piece in the cart
        assertEquals(1, cartItem_labeled.getItemCount());

        assertEquals(3, cartItem_unlabeled.getItemCount());
    }

    @Test
    public void increaseItemCount_isCorrect() {
        assertEquals(3, cartItem_unlabeled.getItemCount());
        cartItem_unlabeled.increaseItemCount();
        assertEquals(4, cartItem_unlabeled.getItemCount());
    }

    @Test
    public void getPrice_isCorrect() {
        assertEquals(new BigDecimal(4.50), cartItem_labeled.getPrice());
        assertEquals(new BigDecimal(13.50), cartItem_unlabeled.getPrice());
    }

    @Test
    public void getFormattedPrice_isCorrect() {
        assertEquals("4.50", cartItem_labeled.getFormattedPrice());
        assertEquals("13.50", cartItem_unlabeled.getFormattedPrice());
    }

    @Test
    public void getFormattedBasePrice_isCorrect() {
        assertEquals("4.50", cartItem_labeled.getFormattedBasePrice());
        assertEquals("4.50", cartItem_unlabeled.getFormattedBasePrice());
    }

    @Test
    public void getFormattedQuantity_isCorrect() {
        assertEquals("3kg", cartItem_labeled.getFormattedQuantity());
        assertEquals("3", cartItem_unlabeled.getFormattedQuantity());
    }

    @Test
    public void setQuantity_isCorrect() {
        assertEquals(new BigDecimal(3), cartItem_unlabeled.getQuantity());
        cartItem_unlabeled.setQuantity(new BigDecimal(5));
        assertEquals(new BigDecimal(5), cartItem_unlabeled.getQuantity());
    }

    @Test
    public void toString_isCorrect() {
        assertEquals(label, cartItem_labeled.toString());
    }
}