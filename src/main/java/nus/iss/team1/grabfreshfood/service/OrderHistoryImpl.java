package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;
import nus.iss.team1.grabfreshfood.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderHistoryImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepo;

    public List<Order> getOrderHistoryForCustomer(String status, Customer customer){
        if (status.equals("Processing") || status.equals("Shipped") || status.equals("Delivered")){
            return orderHistoryRepo.findByOrderStatusAndCustomer(status, customer);
        }
        return orderHistoryRepo.findByCustomer(customer);
    }


}
