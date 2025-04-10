package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.DTO.UpdateCartItemReq;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class GeneralRestController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    //Done by Dionis
    @GetMapping("/cart/customer/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        //first extract Cart for customer
        Cart cart = cartService.findCartByCustomerId(customerId);

        //using CartID from Cart obj to get the list of cartitems.
        List<CartItem> items = cartService.findCartItemsByCartId(cart.getCartId());

        // possible for list to be empty. no need for exceptions
        return new ResponseEntity<>(items, HttpStatus.OK);


    }

    //Done by Dionis (tested)
    @PutMapping("/cart/update-quantity")
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

    @GetMapping("/session/customer-id")
    public ResponseEntity<Integer> getCustomerId(HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Integer customerId = customer.getId();
            return new ResponseEntity<>(customerId, HttpStatus.OK);
        }


    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

}
