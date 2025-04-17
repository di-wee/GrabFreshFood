package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemToCartReq {
    private int customerId;
    private int productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 30, message = "Quantity cannot exceed 30")
    private int quantity;
}
