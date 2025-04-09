package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;
import nus.iss.team1.grabfreshfood.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService ohservice;

    @GetMapping("/order-history")
    public String getOrderHistory(@RequestParam(required = false, defaultValue = "All") String type, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        List<Order> orders = ohservice.getOrderHistoryForCustomer(type, customer);
        model.addAttribute("orders", orders);
        model.addAttribute("selectedType", type);
        return "orderHistory";
    }
}
