package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.model.*;
import nus.iss.team1.grabfreshfood.repository.CustomerRepository;
import nus.iss.team1.grabfreshfood.repository.OrderItemsRepository;
import nus.iss.team1.grabfreshfood.repository.OrderRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderItemsRepository orderItemsRepo;

//show customer order list

    public List<Order> getOrderHistoryForCustomer(String status, Customer customer){
        if (status.equals(OrderStatus.PROCESSING) || status.equals(OrderStatus.SHIPPED) || status.equals(OrderStatus.DELIVERED)){
            return orderRepo.findByOrderStatusAndCustomer(status, customer);
        }
        return orderRepo.findByCustomer(customer);
    }

//create new order to DB and get the orderId for payment

    public int createNewOrderAndId(int customerId, Map<Integer, CartItem> cartItems){
        Order order = new Order();
        order.setCustomer(customerRepo.findById(customerId));
        order.setOrderStatus(OrderStatus.TOPAY);
        order.setOrderDate(LocalDate.now());

        double totalAmount = cartItems.values().stream().filter(CartItem::isCheckout) .mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity()).sum();
        order.setTotalAmount(totalAmount);

        Order saveNewOrder = orderRepo.save(order);

        for (CartItem cartItem : cartItems.values()){
            OrderItems orderItems = new OrderItems();
            orderItems.setOrder(saveNewOrder);
            orderItems.setPrice(cartItem.getPrice() * cartItem.getQuantity());
            orderItems.setQuantity(cartItem.getQuantity());
            orderItems.setProduct(productRepo.findProductById(cartItem.getProductId()));

            orderItemsRepo.save(orderItems);
        }

        return saveNewOrder.getId();
    }
}
