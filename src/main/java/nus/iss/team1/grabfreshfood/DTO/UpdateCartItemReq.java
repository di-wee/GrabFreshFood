package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;

//for scalability
@Getter
@Setter
public class UpdateCartItemReq {
    private int cartId, cartItemId, quantity;
}
