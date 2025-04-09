package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderStatusAndCustomer(String status, Customer customer);
    List<Order> findByCustomer(Customer customer);
}
