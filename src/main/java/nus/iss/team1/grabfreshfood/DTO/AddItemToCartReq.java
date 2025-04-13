package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemToCartReq {
    private int customerId, productId;

}
