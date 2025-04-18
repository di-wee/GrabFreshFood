//DONE BY BEN YEN


package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ReviewDTO {

    @NotNull
    private int productId;

    @NotNull
    private int userId;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    private String comment;

}
