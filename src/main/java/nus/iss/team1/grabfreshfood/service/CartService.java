package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;

import java.util.List;

public interface CartService {
    Cart findCartByCustomerId(int customerId);

    List<CartItem> findCartItemsByCartId(int cartId);

    CartItem findCartItem(int cartId, int cartItemId);

    CartItem updateItemQuantity(int cartId, int cartItemId, int quantity);

    List<CartItem> updateSelectedItems(List<Integer> selectedIds, int customerId);

    String deleteCartItem(int cartId, int itemId);

    CartItem addCartItemToCart(int customerId, int productId);
}
