package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private ProductCategoriesService productCategoriesService;

    // Done by Dionis
    //GET call to retrieve Cart based on Customer ID
    @GetMapping("/cart/customer/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        try {
            Cart cart = cartService.findCartByCustomerId(customerId);

            List<CartItem> items = cartService.findCartItemsByCartId(cart.getCartId());


            // possible for list to be empty. no need for exceptions
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error encountered when retrieving shopping cart (Status Code: " + HttpStatus.INTERNAL_SERVER_ERROR + "): " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    // Done by Dionis (tested)
    //PUT call to update quantity of Cart Item to db.
    @PutMapping("/cart/update-quantity")
    public ResponseEntity<CartItem> updateItemQuantity(@RequestBody UpdateCartItemReq req) {
        try {

            CartItem updatedItem = cartService.updateItemQuantity(
                    req.getCartId(),
                    req.getCartItemId(),
                    req.getQuantity());
            logger.info("CartID: " + req.getCartId());
            logger.info("Updated quantity: " + req.getCartItemId());
            logger.info("Updated quantity: " + req.getQuantity());
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (CartItemNotFoundException e) {
            logger.error("Error encountered when updating quantity of item (Status Code: " + HttpStatus.NOT_FOUND + "): " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Done by Dionis
    @PutMapping("/cart/update-selected")
    public ResponseEntity<List<CartItem>> updateItemsSelected(@RequestBody UpdateSelectedItemsReq req) {
        try {
            List<CartItem> selectedItems = cartService.updateSelectedItems(
                    req.getSelectedIds(),
                    req.getCustomerId()
            );
            return new ResponseEntity<>(selectedItems, HttpStatus.OK);
        } catch (DataAccessException e) {
            logger.error("Error encountered when updating bool isCheckOut for item (Status Code: " + HttpStatus.BAD_REQUEST + "): " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error encountered when updating bool isCheckOut for item (Status Code: " + HttpStatus.INTERNAL_SERVER_ERROR + "): " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Done by Dionis (tested)
    //GET call to retrieve CustomerID from session in Spring.
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


    //Done by Dionis (tested)
    //GET call to retrieve product details based on product ID for the shopping cart details in frontend React.
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product product =
                productService.findProductById(id);
        if (product == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }


    //Done by Lewis
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
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{keyword}")
    public ResponseEntity<List<Product>> categorySubcategory(@PathVariable("keyword") String keyword) {
        List<Product> products = productService.findProductBySubCategory(keyword);
        if (products == null) {
            products = productService.findProductByCategory(keyword);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Done by Dionis
    //POST call for checkout logic for button click of 'checkout' button to create an order based on shopping cart.
//    @PostMapping("/order/create")
//    public ResponseEntity<Integer> createOrder(@RequestBody CreateOrderRequest req) {
//        try {
//            int newOrderId = orderService.createNewOrderAndId(
//                    req.getCustomerId(),
//                    req.getCartItems(),
//                    req.getTotalAmount()
//
//            );
//
//            return new ResponseEntity<>(newOrderId, HttpStatus.CREATED);
//
//        } catch (CustomerNotFound | ProductNotFoundException e) {
//            logger.error("Error encountered when creating Order(Status Code: " + HttpStatus.NOT_FOUND + "): " + e);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (DataAccessException e) {
//            logger.error("Error encountered when saving Order to DB (Status Code: " + HttpStatus.BAD_REQUEST + "): " + e);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            logger.error("Error encountered when creating Order(Status Code: " + HttpStatus.INTERNAL_SERVER_ERROR + "): " + e);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //Done by Dionis (tested)
    //POST call to add item to cart
    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addItemTocart(@RequestBody AddItemToCartReq req) {
        try {
            CartItem addedItem = cartService.addCartItemToCart(
                    req.getCustomerId(),
                    req.getProductId()
            );
            return new ResponseEntity<>(addedItem, HttpStatus.CREATED);

        } catch (CartNotFoundException | CartItemCreationException e) {
            logger.error("Error encountered when adding item to cart (Status Code: " + HttpStatus.NOT_FOUND + "): " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            logger.error("Error encountered when saving cart item to db(Status Code: " + HttpStatus.BAD_REQUEST + "): " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error encountered when adding item to cart (Status Code: " + HttpStatus.INTERNAL_SERVER_ERROR + "): " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //Done by Ben
    //Change Return type to ResponseEntity<{data type of what is to be returned alongside HttpStatus>
    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<Product> products = productCategoriesService.getProductsByCategoryName(categoryName);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //get api for landing page products done by Dionis (tested)
    @GetMapping("/search/landingpage")
    public ResponseEntity<List<Product>> getProductsForLandingPage() {
        try {
            List<Product> productList = productService.findAllProduct();
            List<Product> first10products = productList
                    .stream()
                    .limit(10)
                    .toList();

            return new ResponseEntity<>(first10products, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            logger.error("Error retrieving product list for landing page: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error retrieving product list for landing page: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
