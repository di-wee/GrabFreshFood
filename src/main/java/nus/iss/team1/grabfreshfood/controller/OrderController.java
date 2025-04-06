package nus.iss.team1.grabfreshfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout-page";
    }

    @GetMapping("/payment")
    public String paymentPage() {
        return "payment-page";
    }
}
