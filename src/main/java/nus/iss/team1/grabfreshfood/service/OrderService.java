package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrderHistoryForCustomer(String status, Customer customer);

    int createNewOrderAndId(int customerId, Map<Integer, CartItem> cartItems);
}
