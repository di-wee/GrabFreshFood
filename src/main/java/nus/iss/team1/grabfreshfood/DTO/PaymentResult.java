package nus.iss.team1.grabfreshfood.DTO;

import lombok.Getter;

import java.util.List;

//Done by LIU SHUTING
@Getter
public class PaymentResult {
    private boolean success;
    private List<String> outStockList;

    public PaymentResult(boolean success, List<String> outStockList){
        this.success = success;
        this.outStockList = outStockList;
    }
}
