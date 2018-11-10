package mse.ch.tsm_mobop_app;

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

    @Test
    public void firstConstructor_isCorrect() {
        CartItem item = new CartItem(id, label, description, price, quantity);
        assertEquals(id, item.getId());
        assertEquals(label, item.getLabel());
        assertEquals(description, item.getDescription());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    public void secondConstructor_isCorrect() {
        CartItem item = new CartItem(id, label, description, price, quantity, quantityLabel);
        assertEquals(id, item.getId());
        assertEquals(label, item.getLabel());
        assertEquals(description, item.getDescription());
        assertEquals(quantity, item.getQuantity());
        assertEquals(quantityLabel, item.getQuantityLabel());
    }
}