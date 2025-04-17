package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Order;
import nus.iss.team1.grabfreshfood.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findByOrder(Order order);

}
