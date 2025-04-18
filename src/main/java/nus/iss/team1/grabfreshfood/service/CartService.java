package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    //Done by Dionis
    Cart findCartByCustomerId(int customerId);

    //Done by Dionis
    List<CartItem> findCartItemsByCartId(int cartId);

    //Done by Dionis
    CartItem findCartItem(int cartId, int cartItemId);

    //Done by Dionis
    CartItem updateItemQuantity(int cartId, int cartItemId, int quantity);

    //Done by Dionis
    List<CartItem> updateSelectedItems(List<Integer> selectedIds, int customerId);

    //Done by Dionis
    CartItem addCartItemToCart(int cartId, int productId);

    CartItem addNumberQuantity(int customerId, int productId, int quantity);

    void deleteCartItem(int cartId, int itemId);

    //Done by LIU SHUTING
    List<CartItem> getCheckoutCartItems(int cartId);

    //Done by LIU SHUTING
    void removeCheckoutItemsFromCart(List<CartItem> checkoutItems);

    //Done by LIU SHUTING
    List<CheckoutItemReq> getCheckoutReq(int cartId);

    //Done by LIU SHUTING
    BigDecimal calculateCheckoutSum(List<CheckoutItemReq> checkoutItemReqList);

    //Done by Dionis
    int getCartItemCount(int customerId);
}
