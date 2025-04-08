package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {
    @Autowired
    private CartService cartService;

    //Done by Dionis
    @GetMapping("/cart/customer/{customerId}/items")
    public List<CartItem> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        //first extract Cart for customer
        Cart cart = cartService.findCartByCustomerId(customerId);

        //using CartID from Cart obj to get the list of cartitems.
        return cartService.findCartItemsByCartId(cart.getCartId());
    }


}
