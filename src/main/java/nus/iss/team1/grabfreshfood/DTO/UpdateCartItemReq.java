package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

//for scalability
@Getter
@Setter
public class UpdateCartItemReq {

    private int cartId;
    private int cartItemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 30, message = "Quantity cannot exceed 30")
    private int quantity;
}
