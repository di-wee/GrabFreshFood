package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    //Done by Dionis
    @Query("SELECT c FROM Cart c WHERE c.customerId = :customerId")
    Cart findCartByCustomerId(@Param("customerId") int customerId);

    Cart findCartByCartId(int cartId);
}
