package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.OrderItems;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
//Done by Dionis
public class CreateOrderRequest {
    private int customerId;
    private double totalAmount;
    private List<CartItem> cartItems;
}
