package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.DTO.CreateOrderRequest;
import nus.iss.team1.grabfreshfood.DTO.UpdateCartItemReq;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.config.CustomerNotFound;
import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.model.*;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.OrderService;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GeneralRestController {
    private static final Logger logger = LoggerFactory.getLogger(GeneralRestController.class);
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Done by Dionis
    @GetMapping("/cart/customer/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        // first extract Cart for customer
        Cart cart = cartService.findCartByCustomerId(customerId);

        // using CartID from Cart obj to get the list of cartitems.
        List<CartItem> items = cartService.findCartItemsByCartId(cart.getCartId());

        // possible for list to be empty. no need for exceptions
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    // Done by Dionis (tested)
    @PutMapping("/cart/update-quantity")
    public ResponseEntity<CartItem> updateItemQuantity(@RequestBody UpdateCartItemReq req) {
        try {

            CartItem updatedItem = cartService.updateItemQuantity(
                    req.getCartId(),
                    req.getCartItemId(),
                    req.getQuantity());
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

    /*
     * @GetMapping("/product/{id}") public ResponseEntity<Product>
     * getProduct(@PathVariable("id") int id) { Product product =
     * productService.findProductById(id); if (product == null) { return new
     * ResponseEntity<>(HttpStatus.NOT_FOUND); } else { return new
     * ResponseEntity<>(product, HttpStatus.OK); } }
     */

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Optional<Product> productOptional = productService.findProductById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("itemId") int itemId,
            @PathVariable("cartId") int cartId) {
        try {
            cartService.deleteCartItem(cartId, itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CartItemNotFoundException e) {
            logger.error("Delete failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/order/create")
    public ResponseEntity<Integer> createOrder(@RequestBody CreateOrderRequest req) {
        try {
            int newOrderId = orderService.createNewOrderAndId(
                    req.getCustomerId(),
                    req.getCartItems(),
                    req.getTotalAmount()

            );

            return new ResponseEntity<>(newOrderId, HttpStatus.CREATED);

        } catch (CustomerNotFound | ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
