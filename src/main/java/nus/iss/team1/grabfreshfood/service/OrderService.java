package nus.iss.team1.grabfreshfood.service;


import nus.iss.team1.grabfreshfood.DTO.PaymentResult;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;


import java.util.List;

public interface OrderService {
    //Done by LIU SHUTING
    List<Order> getOrderHistoryForCustomer(String status, Customer customer);

    //Done by LIU SHUTING
    Order getOrderByOrderId(int orderId);

    //Done by LIU SHUTING
    String saveDeliverAddress(String address, String floorNumber, String unitNumber, String postalCode, String buildingName);

    //Done by LIU SHUTING
    PaymentResult makePayment(int orderId, String cardNumber, String cardExpiry, String cvc);

    //Done by LIU SHUTING
    void cancelOrder(int orderId, Customer customer);

    //Done by LIU SHUTING
    int createNewOrderAndGetNewOrderId(Customer customer, String shippingAddress, List<CartItem> checkoutItems);


}
