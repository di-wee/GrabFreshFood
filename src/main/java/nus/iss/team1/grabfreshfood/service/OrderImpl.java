package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.config.CustomerNotFound;
import nus.iss.team1.grabfreshfood.config.OrderCreationException;
import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.model.*;
import nus.iss.team1.grabfreshfood.repository.CustomerRepository;
import nus.iss.team1.grabfreshfood.repository.OrderItemsRepository;
import nus.iss.team1.grabfreshfood.repository.OrderRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderItemsRepository orderItemsRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    //show customer order list
    public List<Order> getOrderHistoryForCustomer(String status, Customer customer) {
        if (status.equals(OrderStatus.PROCESSING) || status.equals(OrderStatus.SHIPPED) || status.equals(OrderStatus.DELIVERED)) {
            return orderRepo.findByOrderStatusAndCustomer(status, customer);
        }
        return orderRepo.findByCustomer(customer);
    }

    // find order by orderId
    public Order getOrderByOrderId(int orderId) {
        return orderRepo.findById(orderId);
    }

    // get the address and update it to DB
    public String saveDeliverAddress(String address, String floorNumber, String unitNumber,String postalCode, String buildingName) {
        StringBuilder deliverAddress = new StringBuilder();

        if (address != null && !address.isEmpty()) {
            deliverAddress.append(address);
        }
        if (floorNumber != null && !floorNumber.isEmpty()) {
            if (deliverAddress.length() > 0) {
                deliverAddress.append(", ");
            }
            deliverAddress.append("#").append(floorNumber);
        }
        if (unitNumber != null && !unitNumber.isEmpty()) {
            if (deliverAddress.length() > 0) {
                deliverAddress.append(" -");
            }
            deliverAddress.append(unitNumber);
        }
        if (buildingName != null && !buildingName.isEmpty()){
            if (deliverAddress.length() > 0) {
                deliverAddress.append(", ");
            }
            deliverAddress.append(buildingName);
        }
        if (postalCode != null && !postalCode.isEmpty()){
            if (deliverAddress.length() > 0) {
                deliverAddress.append("(");
            }
            deliverAddress.append(postalCode);
            deliverAddress.append(")");
        }

        String shippingAddress = deliverAddress.toString();

        return shippingAddress;

    }

    //Lst: create new order to DB and get the orderId
    public int createNewOrderAndGetNewOrderId(Customer customer, String shippingAddress, List<CartItem> checkoutItems){

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.TOPAY);
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(OrderStatus.NOTPAY);

        BigDecimal totalAmount = checkoutItems.stream().map(cartItem -> {
            BigDecimal unitPrice = BigDecimal.valueOf(productRepo.findProductById(cartItem.getProductId()).getPrice());
            BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
            return unitPrice.multiply(quantity);
        }).reduce(BigDecimal.ZERO, BigDecimal::add).add(OrderStatus.SERVICEFEE).setScale(2, RoundingMode.HALF_UP);

        order.setTotalAmount(totalAmount.doubleValue());

        Order saveNewOrder = orderRepo.save(order);

        for (CartItem cartItem : checkoutItems){
            Product product = productRepo.findProductById(cartItem.getProductId());
            if (product == null) {
                    throw new ProductNotFoundException("Product does not exist with ID: " + cartItem.getProductId());
                }

            BigDecimal perItemTotal = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(cartItem.getQuantity())).setScale(2, RoundingMode.HALF_UP);

            OrderItems orderItems = new OrderItems();
            orderItems.setOrder(saveNewOrder);
            orderItems.setProduct(product);
            orderItems.setQuantity(cartItem.getQuantity());
            orderItems.setPrice(perItemTotal.doubleValue());

            orderItemsRepo.save(orderItems);
        }
        return saveNewOrder.getId();
    }

    //pay order and update order status to DB
    public void makePayment(int orderId, String cardNumber, String cardExpiry, String cvc) {
        Order order = getOrderByOrderId(orderId);

        order.setPaymentMethod(OrderStatus.CREDITCARD);
        order.setOrderStatus(OrderStatus.PROCESSING);

        orderRepo.save(order);
    }

    //cancel order by click cancel button on history page
    public void cancelOrder(int orderId, Customer customer) {
        Order order = getOrderByOrderId(orderId);

        if (order != null && Objects.equals(order.getCustomer().getId(), customer.getId()) && order.getOrderStatus().equals(OrderStatus.TOPAY)) {
            order.setOrderStatus(OrderStatus.CANCELED);
            orderRepo.save(order);
        }
    }


//        @Override
//    //create new order to DB and get the orderId for address and payment
//    //Done by Dionis and Shu Ting
//    public int createNewOrderAndId(int customerId, List<CartItem> cartItems, double totalAmount) {
//        try {
//            Customer customer = customerRepo.findCustomerById(customerId);
//            if (customer == null) {
//                throw new CustomerNotFound("Customer does not exist with ID: " + customerId);
//            }
//
//            Order order = new Order();
//            order.setCustomer(customer);
//            order.setOrderStatus(OrderStatus.TOPAY);
//            order.setOrderDate(LocalDate.now());
//            order.setTotalAmount(totalAmount);
//            order.setPaymentMethod(OrderStatus.NOTPAY);
//            //Lst-add a new one paymentMethod for create new order:"Not Pay"
//
//            //we gonna just temporarily save shipping address as customer's existing address,
//            //to prepopulate customer's address in the shipping details page. any changes to re-append to db.
//            order.setShippingAddress(customer.getAddress());
//
//            Order savedOrder = orderRepo.save(order);
//
//            for (CartItem cartItem : cartItems) {
//                Product product = productRepo.findProductById(cartItem.getProductId());
//                if (product == null) {
//                    throw new ProductNotFoundException("Product does not exist with ID: " + cartItem.getProductId());
//                }
//
//                OrderItems orderItem = new OrderItems();
//                orderItem.setOrder(savedOrder);
//                orderItem.setProduct(product);
//                orderItem.setQuantity(cartItem.getQuantity());
//                orderItem.setPrice(product.getPrice() * cartItem.getQuantity());
//
//                orderItemsRepo.save(orderItem);
//            }
//
//            return savedOrder.getId();
//
//        } catch (DataAccessException e) {
//            throw new OrderCreationException("Error found while saving the order: " + e);
//        } catch (Exception e) {
//            throw new OrderCreationException("Error found while creating the order: " + e);
//        }
//    }
}
