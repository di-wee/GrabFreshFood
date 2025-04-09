package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
