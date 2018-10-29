package mse.ch.tsm_mobop_app.cart;

/**
 * This exception is thrown is an item is requested from the cart, but doesn't exist.
 */
public class CartItemNotFoundException extends Exception {
    public CartItemNotFoundException() {
        super();
    }

    public CartItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartItemNotFoundException(String message) {
        super(message);
    }

    public CartItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
