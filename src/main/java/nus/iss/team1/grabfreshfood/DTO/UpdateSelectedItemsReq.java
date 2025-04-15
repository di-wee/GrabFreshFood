package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateSelectedItemsReq {
    int customerId;
    private List<Integer> selectedIds;
}
