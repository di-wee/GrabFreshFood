package nus.iss.team1.grabfreshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int cartItemId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "is_checkout")
    private boolean isCheckout;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore // Do not serialize the whole cart to avoid circular references
    private Cart cart;

    public CartItem() {
    }

    // This method will still be used internally in the backend
    @Transient
    public int getCartId() {
        return (cart != null) ? cart.getCartId() : -1;
    }

    // This is the NEW exposed property for the frontend to get cartId
    @JsonProperty("cartId")
    public int getCartIdForFrontend() {
        return (cart != null) ? cart.getCartId() : -1;
    }
}
