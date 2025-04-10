package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.DTO.UpdateCartItemReq;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class GeneralRestController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(GeneralRestController.class);


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

    @GetMapping("/search/{query}")
    public ResponseEntity<List<Product>> searchResult(@PathVariable("query") String query) {
        List<Product> products = productService.findProductByQuery(query);
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{subcategoryName}")
    public ResponseEntity<List<Product>> categorySubcategory(@PathVariable("subcategoryName")String subcategoryName) {
        List<Product> products = productService.findProductBySubCategory(subcategoryName);
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
