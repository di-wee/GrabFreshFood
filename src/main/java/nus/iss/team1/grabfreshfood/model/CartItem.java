package nus.iss.team1.grabfreshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Cart cart;

    public CartItem() {
    }

    @Transient
    public int getCartId() {
        return cart.getCartId();
    }
    public void addQuantity() {
    	quantity +=1;
    }

	public void setQuantity(int quantity) {
		// TODO Auto-generated method stub
		
	}

	public void setCart(Cart cartByCustomerId) {
		// TODO Auto-generated method stub
		
	}

	public void setProductId(int productId) {
		// TODO Auto-generated method stub
		
	}

}