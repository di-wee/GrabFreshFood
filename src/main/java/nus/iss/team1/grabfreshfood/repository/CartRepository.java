package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
