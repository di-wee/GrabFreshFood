package nus.iss.team1.grabfreshfood.config;

//Done by Dionis
public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
