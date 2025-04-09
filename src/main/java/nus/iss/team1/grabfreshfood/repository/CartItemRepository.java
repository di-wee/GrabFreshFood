package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    //Done by Dionis
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    List<CartItem> findCartItemsByCartId(@Param("cartId") int cartId);

    //Done by Dionis
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.cartItemId = :cartItemId ")
    CartItem findCartItem(@Param("cartItemId") int cartItemId, @Param("cartId") int cartId);


    int cart(Cart cart);
}
