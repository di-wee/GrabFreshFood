package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    //Done by Dionis
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    List<CartItem> findCartItemsByCartId(@Param("cartId") int cartId);

    //Done by Dionis
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.cartItemId = :cartItemId ")
    CartItem findCartItem(@Param("cartItemId") int cartItemId, @Param("cartId") int cartId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.productId = :productId")
    CartItem findCartItemsByProductId(@Param("cartId") int cartId, @Param("productId") int productId);

    //Lst
    @Query("select ci from CartItem ci where ci.cart.cartId = :cartId and ci.isCheckout = true")
    List<CartItem> findCheckoutCartItemByCartId (@Param("cartId") int cartId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :customer AND ci.cartItemId = :productId")
    CartItem findCartItemByProduct(@Param("customerId")int customerId, @Param("productId")int productId);

    // Added to ensure strict match using Spring Data JPA's method naming
    // This fixes the delete issue where matching on both cartId and itemId was not reliable
    Optional<CartItem> findByCartCartIdAndCartItemId(int cartId, int cartItemId);
}
