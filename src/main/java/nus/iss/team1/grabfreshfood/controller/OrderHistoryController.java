package nus.iss.team1.grabfreshfood.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Order;
import nus.iss.team1.grabfreshfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class OrderHistoryController {

    @Autowired
    private OrderService ohservice;

    @GetMapping("/order-history")
    public String getOrderHistory(@RequestParam(required = false, defaultValue = "All") String type, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        List<Order> orders = ohservice.getOrderHistoryForCustomer(type, customer);
        model.addAttribute("orders", orders);
        model.addAttribute("selectedType", type);
        return "orderHistory";
    }

    //press 'checkout' button on cart page, create new order to DB then get the orderId,then go to fill in address
    @PostMapping("/checkout")
    public String checkoutToAddress(HttpSession session, Map<Integer, CartItem> cartItems, Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        int orderId = ohservice.createNewOrderAndId(customer,cartItems);
        model.addAttribute("orderId", orderId);
        return "redirect:/checkout-page?orderId=" + orderId;

    }

    //jump to the address page,add orderId
    @GetMapping("/checkout-page")
    public String checkoutPage(@RequestParam("orderId")int orderId, Model model){
        model.addAttribute("orderId", orderId);
        return "checkout-page";
    }

    //fill and add address to DB by click 'save&continue' button on checkout-page.html
    @PostMapping("/checkout/pay")
    public String getAddress(@RequestParam("orderId") int orderId,
                             @RequestParam("address") String address,
                             @RequestParam("floorNumber") String floorNumber,
                             @RequestParam("unitNumber") String unitNumber){
        ohservice.getAndSaveDeliverAddress(orderId, address, floorNumber, unitNumber);

        return "redirect:/payment-page?orderId="+ orderId;
    }

    //for test, make me can run it without receiving cart data, manually enter an existing orderId in the URL
    @GetMapping("/payment-page")
    public String showPaymentPage(@RequestParam("orderId") int orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "payment-page";
    }

    //click the 'complete payment' button on payment page to update order status and payment method
    @PostMapping("/payment")
    public String payIt (@RequestParam("orderId") int orderId,
                         @RequestParam("cardNumber") String cardNumber,
                         @RequestParam("cardExpiry") String cardExpiry,
                         @RequestParam("CVC") String cvc,
                         Model model){
        if (cardNumber == null || cardExpiry == null || cvc == null || cardNumber.trim().isEmpty() || cardExpiry.trim().isEmpty() || cvc.trim().isEmpty()){
            model.addAttribute("error", "Please fill in the credit card information!");
            model.addAttribute("orderId", orderId);
            return "payment-page";
        }

        ohservice.makePayment(orderId,cardNumber,cardExpiry,cvc);

        String success = "Your order #" + orderId + " is paid successfully!";
        model.addAttribute("message",success);

        return "payment-success";
    }


}
