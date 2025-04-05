package nus.iss.team1.grabfreshfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {
    @GetMapping("/cart")
    public String cartPage(){
        return "shopping-cart";
    }
}
