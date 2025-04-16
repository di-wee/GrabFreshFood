package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrderHistoryForCustomer(String status, Customer customer);

    Order getOrderByOrderId(int orderId);

    String saveDeliverAddress(String address, String floorNumber, String unitNumber, String postalCode, String buildingName);

    void makePayment(int orderId, String cardNumber, String cardExpiry, String cvc);

    void cancelOrder(int orderId, Customer customer);

    int createNewOrderAndGetNewOrderId(Customer customer, String shippingAddress, List<CartItem> checkoutItems);


}
