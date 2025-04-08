package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.DTO.UpdateCartItemReq;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartRestController {
    @Autowired
    private CartService cartService;

    //Done by Dionis
    @GetMapping("/customer/{customerId}/items")
    public List<CartItem> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        //first extract Cart for customer
        Cart cart = cartService.findCartByCustomerId(customerId);

        //using CartID from Cart obj to get the list of cartitems.
        // possible for list to be empty. no need for exceptions
        return cartService.findCartItemsByCartId(cart.getCartId());
    }

    //Done by Dionis (tested)
    @PutMapping("/update-quantity")
    public ResponseEntity<CartItem> updateItemQuantity(@RequestBody UpdateCartItemReq req) {
        try {

            CartItem updatedItem = cartService.updateItemQuantity(
                    req.getCartId(),
                    req.getCartItemId(),
                    req.getQuantity()
            );
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (CartItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
