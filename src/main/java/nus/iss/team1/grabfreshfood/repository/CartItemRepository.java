package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
