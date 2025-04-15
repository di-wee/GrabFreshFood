package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckoutItemReq {
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal perProductTotalAmount;
}
