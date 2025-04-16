package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import nus.iss.team1.grabfreshfood.DTO.AddItemToCartReq;
import nus.iss.team1.grabfreshfood.DTO.CreateOrderRequest;
import nus.iss.team1.grabfreshfood.DTO.UpdateCartItemReq;
import nus.iss.team1.grabfreshfood.DTO.UpdateSelectedItemsReq;
import nus.iss.team1.grabfreshfood.config.*;
import nus.iss.team1.grabfreshfood.model.*;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.OrderService;
import nus.iss.team1.grabfreshfood.service.ProductCategoriesService;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private ProductCategoriesService productCategoriesService;

    // Done by Dionis
    // GET call to retrieve Cart based on Customer ID
    @GetMapping("/cart/customer/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        try {
            Cart cart = cartService.findCartByCustomerId(customerId);
            List<CartItem> items = cartService.findCartItemsByCartId(cart.getCartId());
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving shopping cart: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Done by Dionis (tested)
    // PUT call to update quantity of Cart Item to db.
    @PutMapping("/cart/update-quantity")
    public ResponseEntity<?> updateItemQuantity(@Valid @RequestBody UpdateCartItemReq req, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        try {
            CartItem item = cartService.findCartItem(req.getCartId(), req.getCartItemId());
            Product product = productService.findProductById(item.getProductId());

            if (req.getQuantity() > product.getStock()) {
                return ResponseEntity.badRequest().body("Requested quantity exceeds available stock.");
            }

            CartItem updatedItem = cartService.updateItemQuantity(
                    req.getCartId(),
                    req.getCartItemId(),
                    req.getQuantity()
            );
            logger.info("CartID: " + req.getCartId());
            logger.info("Updated CartItemID: " + req.getCartItemId());
            logger.info("Updated quantity: " + req.getQuantity());
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);

        } catch (CartItemNotFoundException e) {
            logger.error("Cart item not found: " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error updating cart item quantity: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Done by Dionis
    @PutMapping("/cart/update-selected")
    public ResponseEntity<List<CartItem>> updateItemsSelected(@RequestBody UpdateSelectedItemsReq req) {
        try {
            List<CartItem> selectedItems = cartService.updateSelectedItems(
                    req.getSelectedIds(),
                    req.getCustomerId()
            );
            return new ResponseEntity<>(selectedItems, HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.error("Error updating checkout flag: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error updating checkout flag: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Done by Dionis (tested)
    // GET call to retrieve CustomerID from session in Spring.
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

    // Done by Dionis (tested)
    // GET call to retrieve product details based on product ID
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    // Done by Lewis
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

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchResult(@RequestParam("keyword") String query) {
        List<Product> products = productService.findProductByQuery(query);
        return (products == null || products.isEmpty()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{keyword}")
    public ResponseEntity<List<Product>> categorySubcategory(@PathVariable("keyword") String keyword) {
        List<Product> products = productService.findProductBySubCategory(keyword);
        if (products == null || products.isEmpty()) {
            products = productService.findProductByCategory(keyword);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Done by Dionis (tested)
    // POST call to add item to cart
    @PostMapping("/cart/add")
    public ResponseEntity<?> addItemToCart(@Valid @RequestBody AddItemToCartReq req, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        try {
            Product product = productService.findProductById(req.getProductId());
            if (req.getQuantity() > product.getStock()) {
                return ResponseEntity.badRequest().body("Requested quantity exceeds available stock.");
            }

            CartItem addedItem = cartService.addCartItemToCart(
                    req.getCustomerId(),
                    req.getProductId(),
                    req.getQuantity()
            );
            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);

        } catch (CartNotFoundException | CartItemCreationException e) {
            logger.error("Cart or item creation issue: " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            logger.error("DB error when saving cart item: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error when adding item to cart: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Done by Ben
    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<Product> products = productCategoriesService.getProductsByCategoryName(categoryName);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving products by category: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // Done by Dionis (tested)
    // Get API for landing page
    @GetMapping("/search/landingpage")
    public ResponseEntity<List<Product>> getProductsForLandingPage() {
        try {
            List<Product> productList = productService.findAllProduct();
            List<Product> first10products = productList.stream().limit(10).toList();
            return new ResponseEntity<>(first10products, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            logger.error("Landing page products not found: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal error retrieving landing page products: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
