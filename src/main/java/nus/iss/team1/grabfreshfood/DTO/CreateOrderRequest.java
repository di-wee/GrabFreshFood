package nus.iss.team1.grabfreshfood.DTO;

import nus.iss.team1.grabfreshfood.model.OrderItems;

import java.time.LocalDate;
import java.util.List;

public class CreateOrderRequest {
    private int customerId;
    private LocalDate orderDate;
    private double totalAmount;
    private String orderStatus;
    private List<OrderItems> orderItems;
}
