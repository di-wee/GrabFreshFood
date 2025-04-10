package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {
    @GetMapping("/cart")
    public String cartPage(HttpSession session) {
        // check user session. if user not logged in, redirect to login page
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        

        return "shopping-cart";
    }
}
