package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart findCartByCustomerId(int customerId);

    List<CartItem> findCartItemsByCartId(int cartId);

    CartItem findCartItem(int cartId, int cartItemId);

    CartItem updateItemQuantity(int cartId, int cartItemId, int quantity);

    List<CartItem> updateSelectedItems(List<Integer> selectedIds, int customerId);

    CartItem addCartItemToCart(int cartId, int productId);

    // Overloaded method to support quantity and stock validation
    CartItem addCartItemToCart(int customerId, int productId, int quantity);

    CartItem addNumberQuantity(int customerId, int productId, int quantity);

    void deleteCartItem(int cartId, int itemId);

    List<CartItem> getCheckoutCartItems(int cartId);

    void removeCheckoutItemsFromCart(List<CartItem> checkoutItems);

    List<CheckoutItemReq> getCheckoutReq(int cartId);

    BigDecimal calculateCheckoutSum(List<CheckoutItemReq> checkoutItemReqList);

    int getCartItemCount(int customerId);
}
