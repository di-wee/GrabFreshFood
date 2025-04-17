package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.DTO.PaymentResult;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.model.*;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.CategoryService;
import nus.iss.team1.grabfreshfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class OrderHistoryController {

    @Autowired
    private OrderService ohservice;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    private Customer getLogInCustomer(HttpSession session){
        return (Customer) session.getAttribute("customer");
    }

    private boolean isOrderOwnedByThisCustomer(int orderId, HttpSession session) {
        Customer customer = getLogInCustomer(session);
        if (customer == null) return false;
        Order order = ohservice.getOrderByOrderId(orderId);
        return order != null && order.getCustomer().getId() == customer.getId();
    }

    private static final Map<String, String> STATUS_COLOR_MAP = Map.of(
            OrderStatus.TOPAY, "warning",
            OrderStatus.PROCESSING, "primary",
            OrderStatus.SHIPPED, "info",
            OrderStatus.DELIVERED, "success",
            OrderStatus.CANCELLED, "danger",
            "All", "secondary"
    );

    //Lst: show order-history
    @GetMapping("/order-history")
    public String getOrderHistory(@RequestParam(required = false, defaultValue = "All") String type, Model model, HttpSession session) {
        Customer customer = getLogInCustomer(session);
        if (customer == null) {
            return "redirect:/login";
        }

        List<Order> orders = ohservice.getOrderHistoryForCustomer(type, customer);
        List<String> orderStatus = List.of("All", OrderStatus.TOPAY, OrderStatus.PROCESSING, OrderStatus.SHIPPED, OrderStatus.DELIVERED, OrderStatus.CANCELLED);

        model.addAttribute("statusColorMap", STATUS_COLOR_MAP);
        model.addAttribute("orders", orders);
        model.addAttribute("selectedType", type);
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("serviceFee",OrderStatus.SERVICEFEE);

        return "orderHistory";
    }

    //after clicking "checkout" button, go to checkout-page to fill in address, after fill in, then create order and remove checkout cart item
    @GetMapping("/checkout-page")
    public String checkoutPage(Model model, HttpSession session) {
        Customer customer = getLogInCustomer(session);
        if (customer == null) {
            return "redirect:/login";
        }

        //ger cartItems from DB by cartId
        Cart cart;
        try {
            cart = cartService.findCartByCustomerId(customer.getId());
        } catch (CartNotFoundException e){
            log.error("Cart not found for customer ID: {}",  customer.getId());
            return "redirect:/cart";
        }

        List<CartItem> checkoutItems = cartService.getCheckoutCartItems(cart.getCartId());
        if (checkoutItems == null || checkoutItems.isEmpty()){
            return "redirect:/cart";
        }

        List<CheckoutItemReq> checkoutItemReqList = cartService.getCheckoutReq(cart.getCartId());
        BigDecimal totalAmount = cartService.calculateCheckoutSum(checkoutItemReqList);


        //show the customer saved address on this page
        String orderSavedAddress = customer.getAddress();
        if (orderSavedAddress == null || orderSavedAddress.isBlank()){
            orderSavedAddress = null;
        }

        model.addAttribute("cartId",cart.getCartId());
        model.addAttribute("checkoutItems", checkoutItemReqList);
        model.addAttribute("orderTotalAmount", totalAmount);
        model.addAttribute("serviceFee",OrderStatus.SERVICEFEE);
        model.addAttribute("orderSavedAddress",orderSavedAddress);
        return "checkout-page";
    }


    //fill and add address to DB by click 'save&continue' button on checkout-page.html
    @PostMapping("/checkout/pay")
    public String getAddress(@RequestParam(value = "postalCode", required = false) String postalCode,
                             @RequestParam(value = "buildingName", required = false) String buildingName,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "floorNumber", required = false) String floorNumber,
                             @RequestParam(value = "unitNumber", required = false) String unitNumber,
                             @RequestParam("addressOption") String addressOption,
                             @RequestParam("cartId")int cartId,
                             Model model,HttpSession session) {

        Customer customer = getLogInCustomer(session);
        if (customer == null) {
            return "redirect:/login";
        }

        List<CartItem> checkoutItems = cartService.getCheckoutCartItems(cartId);
        if (checkoutItems == null || checkoutItems.isEmpty()){
            return "redirect:/cart";
        }

        String shippingAddress;
        if ("saved".equals(addressOption)){
            shippingAddress = customer.getAddress();
        } else {
            if (address.isBlank() || floorNumber.isBlank() || unitNumber.isBlank()) {
                model.addAttribute("error", "Please fill in valid delivery address!");
                model.addAttribute("cartId",cartId);
                return "checkout-page";
            }
            shippingAddress = ohservice.saveDeliverAddress(address, floorNumber, unitNumber, postalCode, buildingName);
        }

        int orderId = ohservice.createNewOrderAndGetNewOrderId(customer, shippingAddress, checkoutItems);

        cartService.removeCheckoutItemsFromCart(checkoutItems);

        return "redirect:/payment-page?orderId=" + orderId;
    }

    //for test, make me can run it without receiving cart data, manually enter an existing orderId in the URL
    @GetMapping("/payment-page")
    public String showPaymentPage(@RequestParam("orderId") int orderId, Model model, HttpSession session) {
        if (!isOrderOwnedByThisCustomer(orderId, session)) {
            return "redirect:/order-history";
        }

        model.addAttribute("orderId", orderId);
        return "payment-page";
    }

    //click the 'complete payment' button on payment page to update order status and payment method
    @PostMapping("/payment")
    public String payIt(@RequestParam("orderId") int orderId,
                        @RequestParam("cardNumber") String cardNumber,
                        @RequestParam("cardExpiry") String cardExpiry,
                        @RequestParam("CVC") String cvc,
                        Model model, HttpSession session) {
        if (!isOrderOwnedByThisCustomer(orderId, session)) {
            return "redirect:/order-history";
        }

        if (cardNumber == null || cardExpiry == null || cvc == null || cardNumber.trim().isEmpty() || cardExpiry.trim().isEmpty() || cvc.trim().isEmpty()) {
            model.addAttribute("error", "Please fill in the credit card information!");
            model.addAttribute("orderId", orderId);
            return "payment-page";
        }

        PaymentResult result = ohservice.makePayment(orderId, cardNumber, cardExpiry, cvc);

        if (result.isSuccess()){
            String success = "Your order #" + orderId + " is paid successfully!";
            model.addAttribute("message", success);

            return "payment-success";
        } else {
            String outStockName = String.join(", ", result.getOutStockList());
            String failed = "Your order #" + orderId + " payment failed!";

            List<Category> categories=categoryService.getAllCategoriesWithSubcategories();

            model.addAttribute("categories", categories);
            model.addAttribute("failed",failed);
            model.addAttribute("reason", "Insufficient inventory: " + outStockName);
            return "payment-failed";
        }
    }

    //for cancel order
    @PostMapping("/cancel-order")
    public String cancelOrder(@RequestParam("orderId") int orderId, HttpSession session) {
        Customer customer = getLogInCustomer(session);
        if (customer == null) {
            return "redirect:/login";
        }

        ohservice.cancelOrder(orderId, customer);

        return "redirect:/order-history";
    }

}
