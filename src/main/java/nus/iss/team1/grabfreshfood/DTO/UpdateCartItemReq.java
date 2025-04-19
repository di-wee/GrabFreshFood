package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;

//Done byt Dionis
@Getter
@Setter
public class UpdateCartItemReq {
    private int cartId, cartItemId, quantity;
}
