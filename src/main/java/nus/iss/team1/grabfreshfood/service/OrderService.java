package nus.iss.team1.grabfreshfood.service;


import nus.iss.team1.grabfreshfood.DTO.PaymentResult;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;


import java.util.List;

public interface OrderService {
    List<Order> getOrderHistoryForCustomer(String status, Customer customer);

    Order getOrderByOrderId(int orderId);

    String saveDeliverAddress(String address, String floorNumber, String unitNumber, String postalCode, String buildingName);

    PaymentResult makePayment(int orderId, String cardNumber, String cardExpiry, String cvc);

    void cancelOrder(int orderId, Customer customer);

    int createNewOrderAndGetNewOrderId(Customer customer, String shippingAddress, List<CartItem> checkoutItems);


}
