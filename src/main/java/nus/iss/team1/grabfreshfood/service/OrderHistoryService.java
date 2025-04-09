package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;

import java.util.List;

public interface OrderHistoryService {
    List<Order> getOrderHistoryForCustomer(String status, Customer customer);
}
